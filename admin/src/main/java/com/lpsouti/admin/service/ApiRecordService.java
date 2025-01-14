package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.api_record.ApiRecordEditDTO;
import com.lpsouti.admin.dto.api_record.ApiRecordPageDTO;
import com.lpsouti.admin.vo.api_record.ApiRecordQueryVO;
import com.lpsouti.common.vo.PageVO;
import jakarta.validation.Valid;

public interface ApiRecordService {
    PageVO<ApiRecordQueryVO> pageQuery(@Valid ApiRecordPageDTO dto);

    ApiRecordQueryVO queryById(Long id);

    void edit(@Valid ApiRecordEditDTO dto);

    void delete(Long id);
}
