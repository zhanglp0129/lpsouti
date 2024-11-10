package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户余额表
 *
 * @TableName balances
 */
@TableName(value = "balances")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Balance implements Serializable {
    /**
     * 用户余额id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 余额。单位为元
     */
    private BigDecimal balance;

    /**
     * 免费接口余额。每日0点刷新，时区为北京时间
     */
    private BigDecimal freeBalance;

    /**
     * 上次刷新免费接口余额的日期
     */
    private LocalDate refreshDate;

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