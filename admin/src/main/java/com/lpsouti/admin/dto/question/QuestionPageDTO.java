package com.lpsouti.admin.dto.question;

import com.lpsouti.common.annotation.OrderFieldEnum;
import com.lpsouti.common.annotation.QuestionOriginEnum;
import com.lpsouti.common.annotation.QuestionStatusEnum;
import com.lpsouti.common.annotation.QuestionTypeEnum;
import com.lpsouti.common.constant.OrderField;
import com.lpsouti.common.dto.BasePageDTO;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionPageDTO extends BasePageDTO implements Serializable {
    /**
     * 审核通过时间，范围查找左边界
     */
    private LocalDateTime acceptTimeFrom;

    /**
     * 审核通过时间，范围查找右边界
     */
    private LocalDateTime acceptTimeTo;

    /**
     * 创建时间，范围查找左边界
     */
    private LocalDateTime createTimeFrom;

    /**
     * 创建时间，范围查找右边界
     */
    private LocalDateTime createTimeTo;

    /**
     * 排序字段
     */
    @OrderFieldEnum({OrderField.CREATE_TIME, OrderField.ACCEPT_TIME})
    private String orderBy = OrderField.CREATE_TIME;

    /**
     * 题目来源
     */
    @QuestionOriginEnum
    private Byte origin;

    /**
     * 题目状态
     */
    @QuestionStatusEnum
    private Byte status;

    /**
     * 题目类型
     */
    @QuestionTypeEnum
    private Byte type;

    /**
     * 上传用户id
     */
    private Long uploadUserId;
}
