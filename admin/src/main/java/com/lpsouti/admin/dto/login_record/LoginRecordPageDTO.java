package com.lpsouti.admin.dto.login_record;

import com.lpsouti.common.annotation.OrderFieldEnum;
import com.lpsouti.common.constant.OrderField;
import com.lpsouti.common.dto.BasePageDTO;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRecordPageDTO extends BasePageDTO implements Serializable {
    /**
     * 范围查找创建时间左边界
     */
    private LocalDateTime createTimeFrom;

    /**
     * 范围查找创建时间右边界
     */
    private LocalDateTime createTimeTo;

    /**
     * 范围查找过期时间左边界
     */
    private LocalDateTime expireTimeFrom;

    /**
     * 范围查找过期时间右边界
     */
    private LocalDateTime expireTimeTo;

    /**
     * 登陆时ip地址
     */
    private String ip;

    /**
     * 是否下线
     */
    private Boolean isOffline;

    /**
     * 排序规则
     */
    @OrderFieldEnum({OrderField.CREATE_TIME, OrderField.EXPIRE_TIME})
    private String orderBy = OrderField.CREATE_TIME;

    /**
     * 登录角色。1管理员 2普通用户
     */
    private Long role;

    /**
     * 登录token
     */
    private String token;

    /**
     * 登陆时的User Agent。模糊查找
     */
    private String userAgent;

    /**
     * 用户id
     */
    private Long userId;
}
