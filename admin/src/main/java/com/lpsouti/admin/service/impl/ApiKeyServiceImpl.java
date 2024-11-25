package com.lpsouti.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.lpsouti.admin.dto.api_key.ApiKeyAddDTO;
import com.lpsouti.admin.mapper.ApiKeyMapper;
import com.lpsouti.admin.service.ApiKeyService;
import com.lpsouti.admin.vo.api_key.ApiKeyAddVO;
import com.lpsouti.common.entity.ApiKey;
import com.lpsouti.common.utils.SecurityUtil;
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
}
