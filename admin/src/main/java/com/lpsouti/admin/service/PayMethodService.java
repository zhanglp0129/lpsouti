package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.pay_method.PayMethodAddDTO;
import jakarta.validation.Valid;

public interface PayMethodService {
    void add(@Valid PayMethodAddDTO dto);
}
