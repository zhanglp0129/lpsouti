package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * api接口表
 *
 * @TableName apis
 */
@TableName(value = "apis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api implements Serializable {
    /**
     * api接口id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求路径。不能使用路径参数
     */
    private String path;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口描述。markdown格式，会展示在接口文档中
     */
    private String description;

    /**
     * 是否可用
     */
    private Boolean isEnabled;

    /**
     * 是否为免费接口
     */
    private Boolean isFree;

    /**
     * 每次调用消耗余额
     */
    private BigDecimal consumeBalance;

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