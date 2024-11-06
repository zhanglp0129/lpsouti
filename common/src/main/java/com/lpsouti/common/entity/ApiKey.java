package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户api key表
 * @TableName api_keys
 */
@TableName(value ="api_keys")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiKey implements Serializable {
    /**
     * 用户api key id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id。一个用户可以有多个api key
     */
    private Long userId;

    /**
     * 密钥id。一个32字节、36进制整数
     */
    private String secretId;

    /**
     * MD5加密后的密钥
     */
    private String secretKey;

    /**
     * secret_key加密随机盐值
     */
    private String salt;

    /**
     * 是否启用
     */
    private Boolean isEnabled;

    /**
     * 过期时间。null表示永不过期
     */
    private LocalDateTime expireTime;

    /**
     * 是否仅限免费接口
     */
    private Boolean onlyFree;

    /**
     * 备注
     */
    private String note;

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