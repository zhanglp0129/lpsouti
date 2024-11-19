package com.lpsouti.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.lpsouti.admin.dto.order.OrderAddDTO;
import com.lpsouti.admin.dto.order.OrderEditDTO;
import com.lpsouti.admin.mapper.OrderMapper;
import com.lpsouti.admin.service.OrderService;
import com.lpsouti.common.entity.Order;
import com.lpsouti.common.exception.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    public void add(OrderAddDTO dto) {
        // 准备添加实体
        Order order = new Order();
        BeanUtil.copyProperties(dto, order);
        log.debug("order add entity = {}", order);
        // 添加数据
        int rows = orderMapper.insert(order);
        if (rows == 0) {
            throw new CommonException("创建订单失败");
        }
    }

    @Override
    public void edit(OrderEditDTO dto) {
        // 准备修改实体
        Order order = new Order();
        BeanUtil.copyProperties(dto, order);
        log.debug("order edit entity = {}", order);
        // 修改数据
        int rows = orderMapper.updateById(order);
        if (rows == 0) {
            throw new CommonException("修改订单失败");
        }
    }
}
