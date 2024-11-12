package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.login_record.LoginRecordEditDTO;
import com.lpsouti.admin.service.LoginRecordService;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login_record")
@Slf4j
@RequiredArgsConstructor
public class LoginRecordController {

    private final LoginRecordService loginRecordService;

    // 编辑登录记录
    @PutMapping
    public ResultVO<Void> edit(@RequestBody @Valid LoginRecordEditDTO dto) {
        log.info("login record edit dto = {}", dto);
        loginRecordService.edit(dto);
        return ResultVO.success();
    }
}
