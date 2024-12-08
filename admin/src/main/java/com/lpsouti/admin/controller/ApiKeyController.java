package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.api_key.ApiKeyAddDTO;
import com.lpsouti.admin.dto.api_key.ApiKeyEditDTO;
import com.lpsouti.admin.dto.api_key.ApiKeyPageDTO;
import com.lpsouti.admin.service.ApiKeyService;
import com.lpsouti.admin.vo.api_key.ApiKeyAddVO;
import com.lpsouti.common.entity.ApiKey;
import com.lpsouti.common.vo.PageVO;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    // 修改api key
    @PutMapping
    public ResultVO<Void> edit(@RequestBody @Valid ApiKeyEditDTO dto) {
        log.info("api key edit dto = {}", dto);
        apiKeyService.edit(dto);
        return ResultVO.success();
    }

    // 分页查询api key
    @GetMapping("/page")
    public ResultVO<PageVO<ApiKey>> pageQuery(@Valid ApiKeyPageDTO dto) {
        log.info("api key page dto = {}", dto);
        PageVO<ApiKey> vo = apiKeyService.pageQuery(dto);
        return ResultVO.success(vo);
    }
}
