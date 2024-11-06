package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 通知表
 * @TableName notices
 */
@TableName(value ="notices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice implements Serializable {
    /**
     * 通知id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容。html格式
     */
    private String content;

    /**
     * 是否被展示
     */
    private Boolean isShown;

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