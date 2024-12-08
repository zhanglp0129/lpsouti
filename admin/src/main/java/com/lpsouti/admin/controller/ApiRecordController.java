package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.api_record.ApiRecordPageDTO;
import com.lpsouti.admin.service.ApiRecordService;
import com.lpsouti.admin.vo.api_record.ApiRecordQueryVO;
import com.lpsouti.common.vo.PageVO;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}