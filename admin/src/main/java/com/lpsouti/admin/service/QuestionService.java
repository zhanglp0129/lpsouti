package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.question.QuestionAddDTO;
import com.lpsouti.admin.dto.question.QuestionEditDTO;
import jakarta.validation.Valid;

public interface QuestionService {
    void add(@Valid QuestionAddDTO dto);

    void edit(@Valid QuestionEditDTO dto);
}
