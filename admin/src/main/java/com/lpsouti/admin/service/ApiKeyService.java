package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.api_key.ApiKeyAddDTO;
import com.lpsouti.admin.dto.api_key.ApiKeyEditDTO;
import com.lpsouti.admin.vo.api_key.ApiKeyAddVO;
import jakarta.validation.Valid;

public interface ApiKeyService {
    ApiKeyAddVO add(@Valid ApiKeyAddDTO dto);

    void edit(@Valid ApiKeyEditDTO dto);
}
