package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.question.QuestionAddDTO;
import com.lpsouti.admin.dto.question.QuestionEditDTO;
import com.lpsouti.admin.service.QuestionService;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
@Slf4j
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public ResultVO<Void> add(@RequestBody @Valid QuestionAddDTO dto) {
        if (dto.getAnswerContent().getType().compareTo(dto.getType()) != 0) {
            // 答案类型与题目类型不一致
            return ResultVO.error("题目类型错误");
        }
        log.info("question add dto = {}", dto);
        questionService.add(dto);
        return ResultVO.success();
    }

    @PutMapping
    public ResultVO<Void> edit(@RequestBody @Valid QuestionEditDTO dto) {
        log.info("question edit dto = {}", dto);
        questionService.edit(dto);
        return ResultVO.success();
    }
}
