package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.api.ApiAddDTO;
import com.lpsouti.admin.dto.api.ApiEditDTO;
import com.lpsouti.admin.dto.api.ApiPageDTO;
import com.lpsouti.common.entity.Api;
import com.lpsouti.common.vo.PageVO;
import jakarta.validation.Valid;

public interface ApiService {
    void add(@Valid ApiAddDTO dto);

    void edit(@Valid ApiEditDTO dto);

    PageVO<Api> page(@Valid ApiPageDTO dto);
}
