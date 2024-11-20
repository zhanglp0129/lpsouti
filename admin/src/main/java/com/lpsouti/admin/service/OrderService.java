package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.order.OrderAddDTO;
import com.lpsouti.admin.dto.order.OrderEditDTO;
import com.lpsouti.admin.dto.order.OrderPageDTO;
import com.lpsouti.common.entity.Order;
import com.lpsouti.common.vo.PageVO;
import jakarta.validation.Valid;

public interface OrderService {
    void add(@Valid OrderAddDTO dto);

    void edit(@Valid OrderEditDTO dto);

    PageVO<Order> pageQuery(@Valid OrderPageDTO dto);
}
