package com.lpsouti.admin.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddDTO implements Serializable {
    /**
     * 头像url
     */
    @Length(max = 128)
    private String avatarURL;

    /**
     * 邮箱
     */
    @NotEmpty
    @Length(max = 320)
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
    @NotEmpty
    private long role;

    /**
     * 用户状态，1正常 2封禁
     */
    private Long status;

    /**
     * 用户名
     */
    @NotEmpty
    @Length(min = 4, max = 30)
    private String username;
}
