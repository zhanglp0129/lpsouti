package com.lpsouti.admin.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddDTO implements Serializable {
    /**
     * 头像url
     */
    @Length(max = 128)
    @URL
    private String avatarURL;

    /**
     * 邮箱
     */
    @NotEmpty
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
    @NotEmpty
    @Length(min = 8, max = 16)
    private String password;

    /**
     * 用户角色，1管理员 2普通用户
     */
    @NotNull
    @Min(1)
    @Max(2)
    private Integer role;

    /**
     * 用户状态，1正常 2封禁
     */
    @Min(1)
    @Max(2)
    private Integer status = 1;

    /**
     * 用户名
     */
    @NotEmpty
    @Length(min = 4, max = 30)
    private String username;
}
