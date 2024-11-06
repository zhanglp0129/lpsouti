package com.lpsouti.admin.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 登陆token
     */
    private String token;
}
