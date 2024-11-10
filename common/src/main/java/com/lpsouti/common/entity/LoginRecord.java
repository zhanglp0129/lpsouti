package com.lpsouti.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录记录表
 *
 * @TableName login_records
 */
@TableName(value = "login_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRecord implements Serializable {
    /**
     * 登录记录id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 登录token
     */
    private String token;

    /**
     * 登录ip。可用于获取登录所在地
     */
    private String ip;

    /**
     * 登录时的UA。可用于获取登录设备信息
     */
    private String userAgent;

    /**
     * 登录信息过期时间。不存在永不过期的登录信息
     */
    private LocalDateTime expireTime;

    /**
     * 是否下线
     */
    private Boolean isOffline;

    /**
     * 登录角色。1管理员 2普通用户
     */
    private Byte role;

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