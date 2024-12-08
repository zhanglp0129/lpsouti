package com.lpsouti.admin.dto.question;

import com.lpsouti.common.annotation.QuestionFormatEnum;
import com.lpsouti.common.annotation.QuestionOriginEnum;
import com.lpsouti.common.annotation.QuestionStatusEnum;
import com.lpsouti.common.annotation.QuestionTypeEnum;
import com.lpsouti.common.entity.answer.AnswerContent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionEditDTO implements Serializable {
    /**
     * 审核通过时间
     */
    private LocalDateTime acceptTime;

    /**
     * 解析内容
     */
    private String analysisContent;

    /**
     * 解析格式，同题目格式
     */
    @QuestionFormatEnum
    private Byte analysisFormat;

    /**
     * 答案内容，具体格式参考文档
     */
    private AnswerContent answerContent;

    /**
     * 答案格式，同题目格式
     */
    @QuestionFormatEnum
    private Byte answerFormat;

    /**
     * 题目id
     */
    @NotNull
    private Long id;

    /**
     * 题目来源，1用户上传 2搜题无结果
     */
    @QuestionOriginEnum
    private Byte origin;

    /**
     * 题目内容
     */
    private String questionContent;

    /**
     * 题目格式，1纯文本 2图片 3LaTex(用$包裹公式) 4富文本
     */
    @QuestionFormatEnum
    private Byte questionFormat;

    /**
     * 审核通过后奖励的余额
     */
    private BigDecimal rewardBalance;

    /**
     * 题目状态，1待审核 2已通过 3已驳回
     */
    @QuestionStatusEnum
    private Byte status;

    /**
     * 题目类型，1选择题 2多选题 3判断题 4填空题 5解答题 6题组
     */
    @QuestionTypeEnum
    private Byte type;

    /**
     * 上传用户id
     */
    private Long uploadUserId;
}
