package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.pay_method.PayMethodAddDTO;
import com.lpsouti.admin.dto.pay_method.PayMethodEditDTO;
import com.lpsouti.common.entity.PayMethod;
import jakarta.validation.Valid;

import java.util.List;

public interface PayMethodService {
    void add(@Valid PayMethodAddDTO dto);

    void edit(@Valid PayMethodEditDTO dto);

    List<PayMethod> queryAll();

    void delete(Long id);
}
