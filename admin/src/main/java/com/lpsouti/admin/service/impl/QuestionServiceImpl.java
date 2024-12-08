package com.lpsouti.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.lpsouti.admin.dto.question.QuestionAddDTO;
import com.lpsouti.admin.mapper.QuestionMapper;
import com.lpsouti.admin.service.QuestionService;
import com.lpsouti.common.constant.QuestionOrigin;
import com.lpsouti.common.constant.QuestionStatus;
import com.lpsouti.common.entity.Question;
import com.lpsouti.common.exception.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionMapper questionMapper;

    @Override
    public void add(QuestionAddDTO dto) {
        // 创建添加实体
        Question question = new Question();
        BeanUtil.copyProperties(dto, question);
        // 设置其他属性
        question.setOrigin(QuestionOrigin.USER_UPLOAD);
        question.setStatus(QuestionStatus.ACCEPT);
        question.setAcceptTime(LocalDateTime.now());
        log.debug("question add entity = {}", question);

        // 添加数据
        int rows = questionMapper.insert(question);
        if (rows == 0) {
            throw new CommonException("添加题目失败");
        }
    }
}
