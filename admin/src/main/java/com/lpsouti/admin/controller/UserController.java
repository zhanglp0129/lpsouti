package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.LoginDTO;
import com.lpsouti.admin.dto.UserAddDTO;
import com.lpsouti.admin.service.UserService;
import com.lpsouti.admin.vo.LoginVO;
import com.lpsouti.common.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 添加用户
    @PostMapping
    public ResultVO<Object> add(@RequestBody @Validated UserAddDTO userAddDTO) {
        log.info("userAddDTO = {}", userAddDTO);
        userService.add(userAddDTO);
        return ResultVO.success();
    }

    // 登录
    @PostMapping("/login")
    public ResultVO<LoginVO> login(@RequestBody @Validated LoginDTO loginDTO) {
        log.info("loginDTO = {}", loginDTO);
        LoginVO loginVO = userService.login(loginDTO);
        return ResultVO.success(loginVO);
    }

    // 判断系统中是否存在用户
    @GetMapping("/exists")
    public ResultVO<Boolean> exists() {
        Boolean exists = userService.exists();
        return ResultVO.success(exists);
    }
}
