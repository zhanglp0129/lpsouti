package com.lpsouti.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lpsouti.admin.dto.question.QuestionAddDTO;
import com.lpsouti.admin.dto.question.QuestionEditDTO;
import com.lpsouti.admin.dto.question.QuestionPageDTO;
import com.lpsouti.admin.mapper.QuestionMapper;
import com.lpsouti.admin.service.QuestionService;
import com.lpsouti.common.constant.QuestionOrigin;
import com.lpsouti.common.constant.QuestionStatus;
import com.lpsouti.common.entity.Question;
import com.lpsouti.common.exception.CommonException;
import com.lpsouti.common.vo.PageVO;
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
        question.setType(question.getAnswerContent().getType());
        log.debug("question add entity = {}", question);

        // 添加数据
        int rows = questionMapper.insert(question);
        if (rows == 0) {
            throw new CommonException("添加题目失败");
        }
    }

    @Override
    public void edit(QuestionEditDTO dto) {
        // 创建修改实体
        Question question = new Question();
        BeanUtil.copyProperties(dto, question);
        question.setType(question.getAnswerContent() != null ? question.getAnswerContent().getType() : null);
        log.debug("question edit entity = {}", question);
        // 修改数据
        int rows = questionMapper.updateById(question);
        if (rows == 0) {
            throw new CommonException("修改题目错误");
        }
    }

    @Override
    public PageVO<Question> pageQuery(QuestionPageDTO dto) {
        IPage<Question> page = dto.toIPage();
        // 构造查询条件
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(dto.getType() != null, Question::getType, dto.getType())   // 题目类型
                .eq(dto.getOrigin() != null, Question::getOrigin, dto.getOrigin())    // 题目状态
                .eq(dto.getUploadUserId() != null, Question::getUploadUserId, dto.getUploadUserId())  // 上传用户id
                .eq(dto.getStatus() != null, Question::getStatus, dto.getStatus())    // 题目状态
                .ge(dto.getAcceptTimeFrom() != null, Question::getAcceptTime, dto.getAcceptTimeFrom())    // 审核通过时间
                .le(dto.getAcceptTimeTo() != null, Question::getAcceptTime, dto.getAcceptTimeTo())
                .ge(dto.getCreateTimeFrom() != null, Question::getCreateTime, dto.getCreateTimeFrom())    // 创建时间
                .le(dto.getCreateTimeTo() != null, Question::getCreateTime, dto.getCreateTimeTo());
        // 查询数据
        page = questionMapper.selectPage(page, wrapper);
        // 构造返回数据
        PageVO<Question> vo = PageVO.fromIPage(page);
        log.debug("question page query vo = {}", vo);
        return vo;
    }

    @Override
    public Question queryById(Long id) {
        Question question = questionMapper.selectById(id);
        log.debug("question = {}", question);
        return question;
    }
}
