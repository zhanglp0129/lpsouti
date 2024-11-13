package com.lpsouti.admin.service;


import com.lpsouti.admin.dto.login_record.LoginRecordEditDTO;
import com.lpsouti.admin.dto.login_record.LoginRecordPageDTO;
import com.lpsouti.common.entity.LoginRecord;
import com.lpsouti.common.vo.PageVO;
import jakarta.validation.Valid;

public interface LoginRecordService {
    void edit(@Valid LoginRecordEditDTO dto);

    PageVO<LoginRecord> pageQuery(@Valid LoginRecordPageDTO dto);

    LoginRecord queryById(@Valid Long id);

    void delete(@Valid Long id, Boolean offline);
}
