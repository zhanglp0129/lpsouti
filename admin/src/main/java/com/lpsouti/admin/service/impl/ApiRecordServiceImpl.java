package com.lpsouti.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lpsouti.admin.dto.api_record.ApiRecordPageDTO;
import com.lpsouti.admin.mapper.ApiRecordMapper;
import com.lpsouti.admin.service.ApiRecordService;
import com.lpsouti.admin.vo.api_record.ApiRecordQueryVO;
import com.lpsouti.common.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiRecordServiceImpl implements ApiRecordService {
    private final ApiRecordMapper apiRecordMapper;

    @Override
    public PageVO<ApiRecordQueryVO> pageQuery(ApiRecordPageDTO dto) {
        IPage<ApiRecordQueryVO> page = dto.toIPage();
        // 查询数据
        page = apiRecordMapper.pageQuery(page, dto);
        // 构造响应数据
        PageVO<ApiRecordQueryVO> vo = PageVO.fromIPage(page);
        log.debug("api record page vo = {}", vo);
        return vo;
    }
}
