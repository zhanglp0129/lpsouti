package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.UserAddDTO;
import com.lpsouti.admin.service.UserService;
import com.lpsouti.common.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 添加用户
    @PostMapping
    public ResultVO<Object> add(@RequestBody UserAddDTO userAddDTO) {
        log.info("userAddDTO = {}", userAddDTO);
        userService.add(userAddDTO);
        return ResultVO.success();
    }
}
