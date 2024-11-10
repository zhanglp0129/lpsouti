package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.lpsouti.common.entity.answer.AnswerContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 题目表
 *
 * @TableName questions
 */
@TableName(value = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {
    /**
     * 题目id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 题目类型。1选择题 2多选题 3判断题 4填空题 5解答题 6题组
     */
    private Byte type;

    /**
     * 题目格式。1纯文本 2图片 3LaTex(用$包裹公式) 4富文本
     */
    private Byte questionFormat;

    /**
     * 题目内容
     */
    private String questionContent;

    /**
     * 答案格式。同题目格式
     */
    private Byte answerFormat;

    /**
     * 答案内容。一个json字符串，具体格式参考文档
     */
    private AnswerContent answerContent;

    /**
     * 解析格式。同题目格式
     */
    private Byte analysisFormat;

    /**
     * 解析内容
     */
    private String analysisContent;

    /**
     * 题目来源。1用户上传 2搜题无结果
     */
    private Byte origin;

    /**
     * 上传用户id
     */
    private Long uploadUserId;

    /**
     * 题目状态。1待审核 2已通过 3已驳回
     */
    private Byte status;

    /**
     * 审核通过时间
     */
    private LocalDateTime acceptTime;

    /**
     * 审核通过后奖励的余额
     */
    private BigDecimal rewardBalance;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否被删除
     */
    private Boolean isDeleted;
}