package com.lpsouti.admin.dto.api_key;

import com.lpsouti.common.annotation.OrderFieldEnum;
import com.lpsouti.common.constant.OrderField;
import com.lpsouti.common.dto.BasePageDTO;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApiKeyPageDTO extends BasePageDTO implements Serializable {
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
     * 是否启用
     */
    private Boolean isEnable;

    /**
     * 是否仅限免费接口
     */
    private Boolean onlyFree;

    /**
     * 排序字段
     */
    @OrderFieldEnum({OrderField.CREATE_TIME, OrderField.EXPIRE_TIME})
    private String orderBy = OrderField.CREATE_TIME;

    /**
     * 密钥id
     */
    private String secretId;

    /**
     * 用户id
     */
    private Long userId;

}
