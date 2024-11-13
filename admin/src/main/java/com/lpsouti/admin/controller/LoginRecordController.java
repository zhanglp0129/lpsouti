package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.login_record.LoginRecordEditDTO;
import com.lpsouti.admin.dto.login_record.LoginRecordPageDTO;
import com.lpsouti.admin.service.LoginRecordService;
import com.lpsouti.common.entity.LoginRecord;
import com.lpsouti.common.vo.PageVO;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    // 登陆记录分页查询
    @GetMapping("/page")
    public ResultVO<PageVO<LoginRecord>> pageQuery(@Valid LoginRecordPageDTO dto) {
        log.info("login record page query dto = {}", dto);
        PageVO<LoginRecord> vo = loginRecordService.pageQuery(dto);
        return ResultVO.success(vo);
    }

    // 根据id查询登录记录
    @GetMapping("/{id}")
    public ResultVO<LoginRecord> queryById(@PathVariable("id") @Valid Long id) {
        log.info("login record id = {}", id);
        LoginRecord loginRecord = loginRecordService.queryById(id);
        return ResultVO.success(loginRecord);
    }
}
