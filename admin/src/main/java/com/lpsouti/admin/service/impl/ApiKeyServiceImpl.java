package com.lpsouti.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lpsouti.admin.dto.api_key.ApiKeyAddDTO;
import com.lpsouti.admin.dto.api_key.ApiKeyEditDTO;
import com.lpsouti.admin.dto.api_key.ApiKeyPageDTO;
import com.lpsouti.admin.mapper.ApiKeyMapper;
import com.lpsouti.admin.service.ApiKeyService;
import com.lpsouti.admin.vo.api_key.ApiKeyAddVO;
import com.lpsouti.common.entity.ApiKey;
import com.lpsouti.common.exception.CommonException;
import com.lpsouti.common.utils.SecurityUtil;
import com.lpsouti.common.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyMapper apiKeyMapper;

    @Override
    public ApiKeyAddVO add(ApiKeyAddDTO dto) {
        // 构建添加实体
        ApiKey apiKey = new ApiKey();
        BeanUtil.copyProperties(dto, apiKey);

        // 生成secretId, secretKey, salt
        String secretId = SecurityUtil.generateSecretId(dto.getUserId());
        String secretKey = SecurityUtil.generateSecretKey(dto.getUserId());
        String salt = SecurityUtil.generateSalt();
        // 加密secretKey
        String encryptedSecretKey = SecurityUtil.encryptSecretKey(secretKey, salt);
        // 将api key赋值给实体
        apiKey.setSecretId(secretId);
        apiKey.setSecretKey(encryptedSecretKey);
        apiKey.setSalt(salt);
        log.debug("api key = {}", apiKey);

        // 添加数据
        apiKeyMapper.insert(apiKey);

        return new ApiKeyAddVO(secretId, secretKey);
    }

    @Override
    public void edit(ApiKeyEditDTO dto) {
        // 构建修改实体
        ApiKey apiKey = new ApiKey();
        BeanUtil.copyProperties(dto, apiKey);

        // 对密钥加密
        if (apiKey.getSecretKey() != null) {
            String salt = SecurityUtil.generateSalt();
            String encryptedSecretKey = SecurityUtil.encryptSecretKey(apiKey.getSecretKey(), salt);
            apiKey.setSalt(salt);
            apiKey.setSecretKey(encryptedSecretKey);
        }
        log.debug("api key = {}", apiKey);

        // 修改数据
        int rows = apiKeyMapper.updateById(apiKey);
        if (rows == 0) {
            throw new CommonException("修改api key失败");
        }
    }

    @Override
    public PageVO<ApiKey> pageQuery(ApiKeyPageDTO dto) {
        IPage<ApiKey> page = dto.toIPage();
        // 构造分页查询条件
        LambdaQueryWrapper<ApiKey> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(dto.getUserId() != null, ApiKey::getUserId, dto.getUserId())   // 用户id
                .eq(StrUtil.isNotEmpty(dto.getSecretId()), ApiKey::getSecretId, dto.getSecretId())  // 密钥id
                .eq(dto.getIsEnable() != null, ApiKey::getIsEnabled, dto.getIsEnable())   // 是否启用
                .eq(dto.getOnlyFree() != null, ApiKey::getOnlyFree, dto.getOnlyFree())    // 是否仅限免费
                .ge(dto.getExpireTimeFrom() != null, ApiKey::getExpireTime, dto.getExpireTimeFrom())  // 过期时间
                .le(dto.getExpireTimeTo() != null, ApiKey::getExpireTime, dto.getExpireTimeTo())
                .ge(dto.getCreateTimeFrom() != null, ApiKey::getCreateTime, dto.getCreateTimeFrom())  // 创建时间
                .le(dto.getCreateTimeTo() != null, ApiKey::getCreateTime, dto.getCreateTimeTo());
        // 查询数据
        page = apiKeyMapper.selectPage(page, wrapper);

        // 构造vo
        PageVO<ApiKey> vo = PageVO.fromIPage(page);
        log.debug("api key page vo = {}", vo);
        return vo;
    }

    @Override
    public ApiKey queryById(Long id) {
        ApiKey apiKey = apiKeyMapper.selectById(id);
        log.debug("api key = {}", apiKey);
        return apiKey;
    }

    @Override
    public void delete(Long id) {
        int rows = apiKeyMapper.deleteById(id);
        if (rows == 0) {
            throw new CommonException("删除api key失败");
        }
    }
}
