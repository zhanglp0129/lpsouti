package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.api_record.ApiRecordEditDTO;
import com.lpsouti.admin.dto.api_record.ApiRecordPageDTO;
import com.lpsouti.admin.service.ApiRecordService;
import com.lpsouti.admin.vo.api_record.ApiRecordQueryVO;
import com.lpsouti.common.vo.PageVO;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api_record")
@Slf4j
@RequiredArgsConstructor
public class ApiRecordController {
    private final ApiRecordService apiRecordService;

    // 分页查询api调用记录
    @GetMapping("/page")
    public ResultVO<PageVO<ApiRecordQueryVO>> pageQuery(@Valid ApiRecordPageDTO dto) {
        log.info("api record page dto = {}", dto);
        PageVO<ApiRecordQueryVO> vo = apiRecordService.pageQuery(dto);
        return ResultVO.success(vo);
    }

    // 根据id查询api调用记录
    @GetMapping("/{id}")
    public ResultVO<ApiRecordQueryVO> queryById(@PathVariable Long id) {
        log.info("api record id = {}", id);
        ApiRecordQueryVO vo = apiRecordService.queryById(id);
        return ResultVO.success(vo);
    }

    // 修改api调用记录
    @PutMapping
    public ResultVO<Void> edit(@RequestBody @Valid ApiRecordEditDTO dto) {
        log.info("api record edit dto = {}", dto);
        apiRecordService.edit(dto);
        return ResultVO.success();
    }
}
