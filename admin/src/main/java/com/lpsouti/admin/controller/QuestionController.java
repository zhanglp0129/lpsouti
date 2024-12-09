package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.question.QuestionAddDTO;
import com.lpsouti.admin.dto.question.QuestionEditDTO;
import com.lpsouti.admin.dto.question.QuestionPageDTO;
import com.lpsouti.admin.service.QuestionService;
import com.lpsouti.common.entity.Question;
import com.lpsouti.common.vo.PageVO;
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

    // 添加题目
    @PostMapping
    public ResultVO<Void> add(@RequestBody @Valid QuestionAddDTO dto) {
        log.info("question add dto = {}", dto);
        questionService.add(dto);
        return ResultVO.success();
    }

    // 修改题目
    @PutMapping
    public ResultVO<Void> edit(@RequestBody @Valid QuestionEditDTO dto) {
        log.info("question edit dto = {}", dto);
        questionService.edit(dto);
        return ResultVO.success();
    }

    // 分页查询题目
    @GetMapping("/page")
    public ResultVO<PageVO<Question>> pageQuery(@Valid QuestionPageDTO dto) {
        log.info("question page dto = {}", dto);
        PageVO<Question> vo = questionService.pageQuery(dto);
        return ResultVO.success(vo);
    }

    // 根据id查询题目
    @GetMapping("/{id}")
    public ResultVO<Question> queryById(@PathVariable Long id) {
        log.info("question id = {}", id);
        Question question = questionService.queryById(id);
        return ResultVO.success(question);
    }
}
