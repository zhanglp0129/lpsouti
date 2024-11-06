package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * api调用记录表
 * @TableName api_records
 */
@TableName(value ="api_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRecord implements Serializable {
    /**
     * api调用记录id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * api key id
     */
    private Long keyId;

    /**
     * api id
     */
    private Long apiId;

    /**
     * 消耗余额
     */
    private BigDecimal consumeBalance;

    /**
     * 调用者ip
     */
    private String ip;

    /**
     * 调用错误信息。null表示调用成功
     */
    private String err;

    /**
     * 创建时间。接口调用时间
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