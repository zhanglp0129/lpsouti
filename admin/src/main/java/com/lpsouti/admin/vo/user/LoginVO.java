package com.lpsouti.admin.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 登陆token
     */
    private String token;
}
