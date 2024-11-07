package com.lpsouti.admin.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditDTO implements Serializable {
    /**
     * 用户id
     */
    @NotNull
    private Long id;

    /**
     * 头像url
     */
    @Length(max = 128)
    @URL
    private String avatarURL;

    /**
     * 邮箱
     */
    @Length(max = 320)
    @Email
    private String email;

    /**
     * 昵称
     */
    @Length(max = 10)
    private String nickname;

    /**
     * 密码，明文
     */
    @Length(min = 8, max = 16)
    private String password;

    /**
     * 用户角色，1管理员 2普通用户
     */
    @Min(1)
    @Max(2)
    private Byte role;

    /**
     * 用户状态，1正常 2封禁
     */
    @Min(1)
    @Max(2)
    private Byte status;

    /**
     * 用户名
     */
    @Length(min = 4, max = 30)
    private String username;
}
