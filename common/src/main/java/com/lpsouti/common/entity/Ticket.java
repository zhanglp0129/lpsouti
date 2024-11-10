package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 工单表
 *
 * @TableName tickets
 */
@TableName(value = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {
    /**
     * 工单id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 工单类型id
     */
    private Long ticketTypeId;

    /**
     * 工单标题
     */
    private String title;

    /**
     * 工单状态。1草稿 2已提交 3已回复 4已关闭
     */
    private Byte status;

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