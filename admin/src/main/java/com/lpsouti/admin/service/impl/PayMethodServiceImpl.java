package com.lpsouti.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lpsouti.admin.dto.pay_method.PayMethodAddDTO;
import com.lpsouti.admin.dto.pay_method.PayMethodEditDTO;
import com.lpsouti.admin.mapper.PayMethodMapper;
import com.lpsouti.admin.service.PayMethodService;
import com.lpsouti.common.entity.PayMethod;
import com.lpsouti.common.exception.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        log.debug("pay method add entity = {}", payMethod);
        // 添加数据
        int rows = payMethodMapper.insert(payMethod);
        if (rows == 0) {
            throw new CommonException("添加支付方式失败");
        }
    }

    @Override
    public void edit(PayMethodEditDTO dto) {
        // 创建修改实体
        PayMethod payMethod = new PayMethod();
        BeanUtil.copyProperties(dto, payMethod);
        log.debug("pay method update entity = {}", payMethod);
        // 修改数据
        int rows = payMethodMapper.updateById(payMethod);
        if (rows == 0) {
            throw new CommonException("修改支付方式失败");
        }
    }

    @Override
    public List<PayMethod> queryAll() {
        return payMethodMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public void delete(Long id) {
        int rows = payMethodMapper.deleteById(id);
        if (rows == 0) {
            throw new CommonException("删除支付方式失败");
        }
    }
}
