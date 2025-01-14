package com.lpsouti.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lpsouti.admin.dto.order.OrderAddDTO;
import com.lpsouti.admin.dto.order.OrderEditDTO;
import com.lpsouti.admin.dto.order.OrderPageDTO;
import com.lpsouti.admin.mapper.BalanceMapper;
import com.lpsouti.admin.mapper.OrderMapper;
import com.lpsouti.admin.service.OrderService;
import com.lpsouti.common.constant.OrderStatus;
import com.lpsouti.common.entity.Balance;
import com.lpsouti.common.entity.Order;
import com.lpsouti.common.exception.CommonException;
import com.lpsouti.common.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final BalanceMapper balanceMapper;

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

    @Override
    public PageVO<Order> pageQuery(OrderPageDTO dto) {
        IPage<Order> page = dto.toIPage();
        // 构建分页查询条件
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(dto.getUserId() != null, Order::getUserId, dto.getUserId())    // 用户id
                .eq(dto.getPayMethodId() != null, Order::getPayMethodId, dto.getPayMethodId())    // 支付方式
                .ge(dto.getBalanceFrom() != null, Order::getBalance, dto.getBalanceFrom())    // >=余额
                .le(dto.getBalanceTo() != null, Order::getBalance, dto.getBalanceTo())    // <=余额
                .eq(dto.getStatus() != null, Order::getStatus, dto.getStatus())   // 状态
                .ge(dto.getPayTimeFrom() != null, Order::getPayTime, dto.getPayTimeFrom())    // >=支付时间
                .le(dto.getPayTimeTo() != null, Order::getPayTime, dto.getPayTimeTo())    // <=支付时间
                .ge(dto.getCreateTimeFrom() != null, Order::getCreateTime, dto.getCreateTimeFrom())   // >=创建时间
                .le(dto.getCreateTimeTo() != null, Order::getCreateTime, dto.getCreateTimeTo());  // <=创建时间

        // 查询数据
        page = orderMapper.selectPage(page, wrapper);
        // 封装返回结果
        PageVO<Order> vo = PageVO.fromIPage(page);
        log.debug("page query vo = {}", vo);
        return vo;
    }

    @Override
    public Order queryById(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public void delete(Long id) {
        int rows = orderMapper.deleteById(id);
        if (rows == 0) {
            throw new CommonException("删除订单失败");
        }
    }

    @Override
    @Transactional
    public void editStatus(Long id, Byte status, Boolean syncBalance, Boolean syncPayTime) {
        // 先查询订单金额，不需要加锁
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Order::getBalance, Order::getUserId)  // 获取订单的到账余额和用户id
                .eq(Order::getId, id);  // 指定订单id
        Order order = orderMapper.selectOne(queryWrapper);
        if (order == null) {
            throw new CommonException("订单不存在");
        }

        // 构造修改订单条件
        LambdaUpdateWrapper<Order> orderUpdateWrapper = new LambdaUpdateWrapper<>();
        orderUpdateWrapper.set(Order::getStatus, status) // 修改订单状态
                // 订单状态为已支付时，修改支付时间为当前时间
                .set(syncPayTime && OrderStatus.PAID.equals(status), Order::getPayTime, LocalDateTime.now())
                // 订单状态不为已支付时，修改支付时间为null
                .set(syncPayTime && !OrderStatus.PAID.equals(status), Order::getPayTime, null)
                .eq(Order::getId, id);  // 指定订单id

        // 构造修改余额条件
        LambdaUpdateWrapper<Balance> balanceUpdateWrapper = new LambdaUpdateWrapper<>();
        balanceUpdateWrapper
                // 订单状态为已支付时，增加余额
                .setIncrBy(syncBalance && OrderStatus.PAID.equals(status), Balance::getBalance, order.getBalance())
                // 订单状态不为已支付时，扣减余额
                .setDecrBy(syncBalance && !OrderStatus.PAID.equals(status), Balance::getBalance, order.getBalance())
                .eq(Balance::getUserId, order.getUserId());

        // 修改数据，必须同时修改成功
        int rows = orderMapper.update(orderUpdateWrapper);
        rows *= balanceMapper.update(balanceUpdateWrapper);
        if (rows == 0) {
            throw new CommonException("修改订单状态失败");
        }
    }
}
