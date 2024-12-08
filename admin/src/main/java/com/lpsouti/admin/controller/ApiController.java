package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.api.ApiAddDTO;
import com.lpsouti.admin.dto.api.ApiEditDTO;
import com.lpsouti.admin.dto.api.ApiPageDTO;
import com.lpsouti.admin.service.ApiService;
import com.lpsouti.common.entity.Api;
import com.lpsouti.common.vo.PageVO;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    // 修改api接口
    @PutMapping
    public ResultVO<Void> edit(@RequestBody @Valid ApiEditDTO dto) {
        log.info("api edit dto = {}", dto);
        apiService.edit(dto);
        return ResultVO.success();
    }

    @GetMapping("/page")
    public ResultVO<PageVO<Api>> pageQuery(@Valid ApiPageDTO dto) {
        log.info("api page dto = {}", dto);
        PageVO<Api> vo = apiService.pageQuery(dto);
        return ResultVO.success(vo);
    }

    @GetMapping("/{id}")
    public ResultVO<Api> queryById(@PathVariable("id") Long id) {
        log.info("api id = {}", id);
        Api vo = apiService.queryById(id);
        return ResultVO.success(vo);
    }
}
