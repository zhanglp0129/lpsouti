package com.lpsouti.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lpsouti.admin.dto.api_record.ApiRecordPageDTO;
import com.lpsouti.admin.vo.api_record.ApiRecordQueryVO;
import com.lpsouti.common.entity.ApiRecord;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * @author zhanglp0129
 * @description 针对表【api_records(api调用记录表)】的数据库操作Mapper
 * @createDate 2024-11-05 17:18:44
 * @Entity com.lpsouti.common.entity.ApiRecord
 */
public interface ApiRecordMapper extends BaseMapper<ApiRecord> {
    // 分页查询api调用记录
    IPage<ApiRecordQueryVO> pageQuery(@Param("page") IPage<ApiRecordQueryVO> page,
                                      @Param("dto") ApiRecordPageDTO dto);

}




