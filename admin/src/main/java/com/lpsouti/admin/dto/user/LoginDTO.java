package com.lpsouti.admin.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO implements Serializable {
    /**
     * 邮箱
     */
    @Length(max = 320)
    @Email
    private String email;

    /**
     * 密码
     */
    @NotEmpty
    @Length(min = 8, max = 16)
    private String password;

    /**
     * 用户名
     */
    @Length(max = 30)
    private String username;
}
