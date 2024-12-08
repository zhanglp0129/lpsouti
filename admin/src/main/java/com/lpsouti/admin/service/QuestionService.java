package com.lpsouti.admin.service;

import com.lpsouti.admin.dto.question.QuestionAddDTO;
import jakarta.validation.Valid;

public interface QuestionService {
    void add(@Valid QuestionAddDTO dto);
}
