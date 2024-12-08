package com.lpsouti.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lpsouti.admin.dto.api.ApiAddDTO;
import com.lpsouti.admin.dto.api.ApiEditDTO;
import com.lpsouti.admin.dto.api.ApiPageDTO;
import com.lpsouti.admin.mapper.ApiMapper;
import com.lpsouti.admin.service.ApiService;
import com.lpsouti.common.entity.Api;
import com.lpsouti.common.exception.CommonException;
import com.lpsouti.common.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    private final ApiMapper apiMapper;

    @Override
    public void add(ApiAddDTO dto) {
        // 构造添加实体
        Api api = new Api();
        BeanUtil.copyProperties(dto, api);
        log.debug("api = {}", api);
        // 添加数据
        int rows = apiMapper.insert(api);
        if (rows == 0) {
            throw new CommonException("添加api接口失败");
        }
    }

    @Override
    public void edit(ApiEditDTO dto) {
        // 构造修改实体
        Api api = new Api();
        BeanUtil.copyProperties(dto, api);
        log.debug("api = {}", api);
        // 修改数据
        int rows = apiMapper.updateById(api);
        if (rows == 0) {
            throw new CommonException("修改api接口失败");
        }
    }

    @Override
    public PageVO<Api> pageQuery(ApiPageDTO dto) {
        IPage<Api> page = dto.toIPage();
        // 构造分页查询条件
        LambdaQueryWrapper<Api> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotEmpty(dto.getName()), Api::getName, dto.getName())    // 名称模糊匹配
                .eq(dto.getIsEnabled() != null, Api::getIsEnabled, dto.getIsEnabled())    // 是否启用
                .eq(dto.getIsFree() != null, Api::getIsFree, dto.getIsFree()) // 是否免费
                .ge(dto.getCreateTimeFrom() != null, Api::getCreateTime, dto.getCreateTimeFrom()) // 创建时间
                .le(dto.getCreateTimeTo() != null, Api::getCreateTime, dto.getCreateTimeTo());
        // 查询数据
        page = apiMapper.selectPage(page, wrapper);
        // 构造响应数据
        PageVO<Api> vo = PageVO.fromIPage(page);
        log.debug("api page vo = {}", vo);
        return vo;
    }

    @Override
    public Api queryById(Long id) {
        Api api = apiMapper.selectById(id);
        log.debug("api = {}", api);
        return api;
    }
}
