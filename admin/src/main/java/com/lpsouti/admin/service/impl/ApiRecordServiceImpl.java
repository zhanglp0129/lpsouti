package com.lpsouti.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lpsouti.admin.dto.api_record.ApiRecordEditDTO;
import com.lpsouti.admin.dto.api_record.ApiRecordPageDTO;
import com.lpsouti.admin.mapper.ApiRecordMapper;
import com.lpsouti.admin.service.ApiRecordService;
import com.lpsouti.admin.vo.api_record.ApiRecordQueryVO;
import com.lpsouti.common.entity.ApiRecord;
import com.lpsouti.common.exception.CommonException;
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

    @Override
    public ApiRecordQueryVO queryById(Long id) {
        ApiRecordQueryVO vo = apiRecordMapper.queryById(id);
        log.debug("query api record by id vo = {}", vo);
        return vo;
    }

    @Override
    public void edit(ApiRecordEditDTO dto) {
        // 构造修改实体
        ApiRecord apiRecord = new ApiRecord();
        BeanUtil.copyProperties(dto, apiRecord);
        log.debug("api record update entity = {}", apiRecord);
        // 构造修改条件
        LambdaUpdateWrapper<ApiRecord> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Boolean.TRUE.equals(dto.getSuccess()), ApiRecord::getErr, null)
                .set(Boolean.FALSE.equals(dto.getSuccess()) && dto.getErr() == null, ApiRecord::getErr, "")
                .eq(ApiRecord::getId, dto.getId());
        // 修改数据
        int rows = apiRecordMapper.update(apiRecord, wrapper);
        if (rows == 0) {
            throw new CommonException("修改api调用记录失败");
        }
    }

    @Override
    public void delete(Long id) {
        int rows = apiRecordMapper.deleteById(id);
        if (rows == 0) {
            throw new CommonException("删除api调用记录失败");
        }
    }
}
