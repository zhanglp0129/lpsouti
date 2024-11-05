package com.lpsouti.admin.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotEmpty
    @Length(min = 8, max = 16)
    private String password;

    /**
     * 用户角色，1管理员 2普通用户
     */
    @NotNull
    @Min(1)
    @Max(2)
    private Long role;

    /**
     * 用户状态，1正常 2封禁
     */
    @Min(1)
    @Max(2)
    private Long status;

    /**
     * 用户名
     */
    @NotEmpty
    @Length(min = 4, max = 30)
    private String username;
}
