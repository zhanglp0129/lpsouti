package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.question.QuestionAddDTO;
import com.lpsouti.admin.dto.question.QuestionEditDTO;
import com.lpsouti.admin.dto.question.QuestionPageDTO;
import com.lpsouti.common.entity.Question;
import com.lpsouti.common.vo.PageVO;
import jakarta.validation.Valid;

public interface QuestionService {
    void add(@Valid QuestionAddDTO dto);

    void edit(@Valid QuestionEditDTO dto);

    PageVO<Question> pageQuery(@Valid QuestionPageDTO dto);

    Question queryById(Long id);

    void delete(Long id);
}
