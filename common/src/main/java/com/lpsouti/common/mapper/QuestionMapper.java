package com.lpsouti.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lpsouti.common.entity.Question;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zhanglp0129
* @description 针对表【questions(题目表)】的数据库操作Mapper
* @createDate 2024-11-05 17:18:44
* @Entity com.lpsouti.common.entity.Question
*/
public interface QuestionMapper extends BaseMapper<Question> {

}




