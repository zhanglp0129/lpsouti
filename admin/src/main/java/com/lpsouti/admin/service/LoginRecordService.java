package com.lpsouti.admin.service;


import com.lpsouti.admin.dto.login_record.LoginRecordEditDTO;
import jakarta.validation.Valid;

public interface LoginRecordService {
    void edit(@Valid LoginRecordEditDTO dto);
}
