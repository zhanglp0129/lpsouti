package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 支付方式表
 * @TableName pay_methods
 */
@TableName(value ="pay_methods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayMethod implements Serializable {
    /**
     * 支付方式id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 支付方式名称
     */
    private String name;

    /**
     * 支付货币。用英文缩写
     */
    private String currency;

    /**
     * 充值倍率。充值1单位货币可以获得多少余额
     */
    private BigDecimal rate;

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