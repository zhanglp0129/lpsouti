package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.user.LoginDTO;
import com.lpsouti.admin.dto.user.UserAddDTO;
import com.lpsouti.admin.dto.user.UserEditDTO;
import com.lpsouti.admin.dto.user.UserPageDTO;
import com.lpsouti.admin.service.UserService;
import com.lpsouti.admin.vo.user.LoginVO;
import com.lpsouti.admin.vo.user.UserVO;
import com.lpsouti.common.vo.PageVO;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
    public ResultVO<Object> add(@RequestBody @Valid UserAddDTO userAddDTO) {
        log.info("userAddDTO = {}", userAddDTO);
        userService.add(userAddDTO);
        return ResultVO.success();
    }

    // 登录
    @PostMapping("/login")
    public ResultVO<LoginVO> login(@RequestBody @Valid LoginDTO loginDTO) {
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

    // 修改用户
    @PutMapping
    public ResultVO<Object> edit(@RequestBody @Valid UserEditDTO userEditDTO) {
        log.info("userEditDTO = {}", userEditDTO);
        userService.edit(userEditDTO);
        return ResultVO.success();
    }

    // 分页查询用户
    @GetMapping("/page")
    public ResultVO<PageVO<UserVO>> page(@Valid UserPageDTO userPageDTO) {
        log.info("userPageDTO = {}", userPageDTO);
        PageVO<UserVO> vo = userService.page(userPageDTO);
        return ResultVO.success(vo);
    }
}
