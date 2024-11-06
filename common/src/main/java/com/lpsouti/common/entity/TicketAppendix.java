package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 工单附件表
 * @TableName ticket_appendices
 */
@TableName(value ="ticket_appendices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketAppendix implements Serializable {
    /**
     * 工单附件id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 工单内容id
     */
    private Long ticketContentId;

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