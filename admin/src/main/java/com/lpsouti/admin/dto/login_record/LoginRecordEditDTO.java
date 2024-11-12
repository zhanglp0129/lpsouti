package com.lpsouti.admin.dto.login_record;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRecordEditDTO {
    /**
     * 登录信息过期时间，不存在永不过期的登录信息
     */
    private LocalDateTime expireTime;

    /**
     * 登录记录id
     */
    @NotNull
    private Long id;

    /**
     * 登录ip，可用于获取登录所在地
     */
    @Length(max = 45)
    private String ip;

    /**
     * 是否下线
     */
    private Boolean isOffline;

    /**
     * 登录角色，1管理员 2普通用户
     */
    @Min(1)
    @Max(2)
    private Byte role;

    /**
     * 登录token
     */
    @Length(max = 100)
    private String token;

    /**
     * 登录时的UA，可用于获取登录设备信息
     */
    @Length(max = 200)
    private String userAgent;

    /**
     * 用户id
     */
    private Long userId;
}
