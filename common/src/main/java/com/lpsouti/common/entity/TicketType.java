package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 工单类型
 * @TableName ticket_types
 */
@TableName(value ="ticket_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketType implements Serializable {
    /**
     * 工单类型id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 工单类型名称
     */
    private String name;

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