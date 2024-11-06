package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目表
 * @TableName questions
 */
@TableName(value ="questions")
@Data
public class Question implements Serializable {
    /**
     * 题目id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 题目类型。1选择题 2多选题 3判断题 4填空题 5解答题 6题组
     */
    private Integer type;

    /**
     * 题目格式。1纯文本 2图片 3LaTex(用$包裹公式) 4富文本
     */
    private Integer questionFormat;

    /**
     * 题目内容
     */
    private String questionContent;

    /**
     * 答案格式。同题目格式
     */
    private Integer answerFormat;

    /**
     * 答案内容。一个json字符串，具体格式参考文档
     */
    private Object answerContent;

    /**
     * 解析格式。同题目格式
     */
    private Integer analysisFormat;

    /**
     * 解析内容
     */
    private String analysisContent;

    /**
     * 题目来源。1用户上传 2搜题无结果
     */
    private Integer origin;

    /**
     * 上传用户id
     */
    private Long uploadUserId;

    /**
     * 题目状态。1待审核 2已通过 3已驳回
     */
    private Integer status;

    /**
     * 审核通过时间
     */
    private Date acceptTime;

    /**
     * 审核通过后奖励的余额
     */
    private Integer rewardBalance;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否被删除
     */
    private Boolean isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Question other = (Question) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getQuestionFormat() == null ? other.getQuestionFormat() == null : this.getQuestionFormat().equals(other.getQuestionFormat()))
            && (this.getQuestionContent() == null ? other.getQuestionContent() == null : this.getQuestionContent().equals(other.getQuestionContent()))
            && (this.getAnswerFormat() == null ? other.getAnswerFormat() == null : this.getAnswerFormat().equals(other.getAnswerFormat()))
            && (this.getAnswerContent() == null ? other.getAnswerContent() == null : this.getAnswerContent().equals(other.getAnswerContent()))
            && (this.getAnalysisFormat() == null ? other.getAnalysisFormat() == null : this.getAnalysisFormat().equals(other.getAnalysisFormat()))
            && (this.getAnalysisContent() == null ? other.getAnalysisContent() == null : this.getAnalysisContent().equals(other.getAnalysisContent()))
            && (this.getOrigin() == null ? other.getOrigin() == null : this.getOrigin().equals(other.getOrigin()))
            && (this.getUploadUserId() == null ? other.getUploadUserId() == null : this.getUploadUserId().equals(other.getUploadUserId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAcceptTime() == null ? other.getAcceptTime() == null : this.getAcceptTime().equals(other.getAcceptTime()))
            && (this.getRewardBalance() == null ? other.getRewardBalance() == null : this.getRewardBalance().equals(other.getRewardBalance()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getQuestionFormat() == null) ? 0 : getQuestionFormat().hashCode());
        result = prime * result + ((getQuestionContent() == null) ? 0 : getQuestionContent().hashCode());
        result = prime * result + ((getAnswerFormat() == null) ? 0 : getAnswerFormat().hashCode());
        result = prime * result + ((getAnswerContent() == null) ? 0 : getAnswerContent().hashCode());
        result = prime * result + ((getAnalysisFormat() == null) ? 0 : getAnalysisFormat().hashCode());
        result = prime * result + ((getAnalysisContent() == null) ? 0 : getAnalysisContent().hashCode());
        result = prime * result + ((getOrigin() == null) ? 0 : getOrigin().hashCode());
        result = prime * result + ((getUploadUserId() == null) ? 0 : getUploadUserId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAcceptTime() == null) ? 0 : getAcceptTime().hashCode());
        result = prime * result + ((getRewardBalance() == null) ? 0 : getRewardBalance().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", questionFormat=").append(questionFormat);
        sb.append(", questionContent=").append(questionContent);
        sb.append(", answerFormat=").append(answerFormat);
        sb.append(", answerContent=").append(answerContent);
        sb.append(", analysisFormat=").append(analysisFormat);
        sb.append(", analysisContent=").append(analysisContent);
        sb.append(", origin=").append(origin);
        sb.append(", uploadUserId=").append(uploadUserId);
        sb.append(", status=").append(status);
        sb.append(", acceptTime=").append(acceptTime);
        sb.append(", rewardBalance=").append(rewardBalance);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}