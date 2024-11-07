package com.lpsouti.admin.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO implements Serializable {
    /**
     * 邮箱
     */
    @Email
    private String email;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

    /**
     * 用户名
     */
    private String username;
}
