package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.order.OrderAddDTO;
import com.lpsouti.admin.dto.order.OrderEditDTO;
import jakarta.validation.Valid;

public interface OrderService {
    void add(@Valid OrderAddDTO dto);

    void edit(@Valid OrderEditDTO dto);
}
