package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.order.OrderAddDTO;
import jakarta.validation.Valid;

public interface OrderService {
    void add(@Valid OrderAddDTO dto);
}
