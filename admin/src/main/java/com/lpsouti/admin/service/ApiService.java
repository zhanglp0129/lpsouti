package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.api.ApiAddDTO;
import jakarta.validation.Valid;

public interface ApiService {
    void add(@Valid ApiAddDTO dto);
}
