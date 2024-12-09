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
        // 检查答案类型与题目类型
        // 未修改题目类型
        boolean notChangeType = dto.getType() == null && dto.getAnswerContent() == null;
        // 修改后的题目类型相同
        boolean typeEqual = dto.getType() != null && dto.getAnswerContent() != null &&
                dto.getType().compareTo(dto.getAnswerContent().getType()) == 0;
        if (!notChangeType && !typeEqual) {
            return ResultVO.error("题目类型错误");
        }

        log.info("question edit dto = {}", dto);
        questionService.edit(dto);
        return ResultVO.success();
    }
}
