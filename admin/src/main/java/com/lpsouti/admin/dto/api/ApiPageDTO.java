package com.lpsouti.admin.dto.api;

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
public class ApiPageDTO extends BasePageDTO implements Serializable {
    /**
     * 范围查找创建时间左边界
     */
    private LocalDateTime createTimeFrom;

    /**
     * 范围查找创建时间右边界
     */
    private LocalDateTime createTimeTo;

    /**
     * 是否可用
     */
    private Boolean isEnabled;

    /**
     * 是否是免费接口
     */
    private Boolean isFree;

    /**
     * 接口名称。模糊查找
     */
    private String name;

    /**
     * 排序字段
     */
    @OrderFieldEnum({OrderField.CREATE_TIME, OrderField.CONSUME_BALANCE})
    private String orderBy = OrderField.CREATE_TIME;
}
