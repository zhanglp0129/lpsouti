package com.lpsouti.admin.dto.question;

import com.lpsouti.common.annotation.QuestionFormatEnum;
import com.lpsouti.common.entity.answer.AnswerContent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAddDTO implements Serializable {
    /**
     * 解析内容
     */
    private String analysisContent;

    /**
     * 解析格式，同题目格式
     */
    @NotNull
    @QuestionFormatEnum
    private Byte analysisFormat;

    /**
     * 答案内容，具体格式参考文档
     */
    private AnswerContent answerContent;

    /**
     * 答案格式，同题目格式
     */
    @NotNull
    @QuestionFormatEnum
    private Byte answerFormat;

    /**
     * 题目内容
     */
    private String questionContent;

    /**
     * 题目格式，1纯文本 2图片 3LaTex(用$包裹公式) 4富文本
     */
    @NotNull
    @QuestionFormatEnum
    private Byte questionFormat;

    /**
     * 上传用户id
     */
    private Long uploadUserId;

}
