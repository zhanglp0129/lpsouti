package com.lpsouti.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.lpsouti.admin.dto.pay_method.PayMethodAddDTO;
import com.lpsouti.admin.mapper.PayMethodMapper;
import com.lpsouti.admin.service.PayMethodService;
import com.lpsouti.common.entity.PayMethod;
import com.lpsouti.common.exception.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PayMethodServiceImpl implements PayMethodService {

    private final PayMethodMapper payMethodMapper;

    @Override
    public void add(PayMethodAddDTO dto) {
        // 创建实体
        PayMethod payMethod = new PayMethod();
        BeanUtil.copyProperties(dto, payMethod);
        // 添加数据
        int rows = payMethodMapper.insert(payMethod);
        if (rows == 0) {
            throw new CommonException("添加支付方式失败");
        }
    }
}
