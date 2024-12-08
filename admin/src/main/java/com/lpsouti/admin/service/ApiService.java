package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.api.ApiAddDTO;
import com.lpsouti.admin.dto.api.ApiEditDTO;
import jakarta.validation.Valid;

public interface ApiService {
    void add(@Valid ApiAddDTO dto);

    void edit(@Valid ApiEditDTO dto);
}
