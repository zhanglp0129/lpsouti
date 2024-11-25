package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.api_key.ApiKeyAddDTO;
import com.lpsouti.admin.service.ApiKeyService;
import com.lpsouti.admin.vo.api_key.ApiKeyAddVO;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api_key")
@RequiredArgsConstructor
@Slf4j
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    // 创建api key
    @PostMapping
    public ResultVO<ApiKeyAddVO> add(@RequestBody @Valid ApiKeyAddDTO dto) {
        log.info("api key add dto = {}", dto);
        ApiKeyAddVO vo = apiKeyService.add(dto);
        return ResultVO.success(vo);
    }
}
