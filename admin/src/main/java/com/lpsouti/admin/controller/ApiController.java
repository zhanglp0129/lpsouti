package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.api.ApiAddDTO;
import com.lpsouti.admin.service.ApiService;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open_api")
@Slf4j
@RequiredArgsConstructor
public class ApiController {
    private final ApiService apiService;

    // 添加api接口
    @PostMapping
    public ResultVO<Void> add(@RequestBody @Valid ApiAddDTO dto) {
        log.info("api add dto = {}", dto);
        apiService.add(dto);
        return ResultVO.success();
    }
}
