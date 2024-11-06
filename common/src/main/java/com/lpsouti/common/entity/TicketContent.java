package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 工单内容表
 * @TableName ticket_content
 */
@TableName(value ="ticket_content")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketContent implements Serializable {
    /**
     * 工单内容id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 工单id
     */
    private Long ticketId;

    /**
     * 工单内容
     */
    private String content;

    /**
     * 作者。1用户 2客服
     */
    private Byte author;

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