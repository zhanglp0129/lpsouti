package com.lpsouti.admin.vo.user;

import com.lpsouti.common.entity.Balance;
import com.lpsouti.common.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {
    /**
     * 余额
     */
    private Balance balance;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户信息
     */
    private UserInfo info;

    /**
     * 是否被删除
     */
    private Boolean isDeleted;

    /**
     * 用户角色，1管理员 2普通用户
     */
    private Byte role;

    /**
     * 用户状态，1正常 2封禁
     */
    private Byte status;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 用户名
     */
    private String username;
}
