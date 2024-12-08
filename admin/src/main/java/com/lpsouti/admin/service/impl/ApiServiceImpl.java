package com.lpsouti.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.lpsouti.admin.dto.api.ApiAddDTO;
import com.lpsouti.admin.mapper.ApiMapper;
import com.lpsouti.admin.service.ApiService;
import com.lpsouti.common.entity.Api;
import com.lpsouti.common.exception.CommonException;
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
}
