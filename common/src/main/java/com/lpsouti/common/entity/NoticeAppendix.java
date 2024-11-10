package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通知附件表
 *
 * @TableName notice_appendices
 */
@TableName(value = "notice_appendices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeAppendix implements Serializable {
    /**
     * 通知附件id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 通知id
     */
    private Long noticeId;

    /**
     * 附件url
     */
    private String appendixUrl;

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