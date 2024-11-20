package com.lpsouti.admin.dto.order;

import com.lpsouti.common.annotation.OrderFieldEnum;
import com.lpsouti.common.constant.OrderField;
import com.lpsouti.common.dto.BasePageDTO;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPageDTO extends BasePageDTO implements Serializable {
    /**
     * 范围查找到账余额的左边界
     */
    private BigDecimal balanceFrom;

    /**
     * 范围查找到账余额的右边界
     */
    private BigDecimal balanceTo;

    /**
     * 范围查找订单创建时间的左边界
     */
    private LocalDateTime createTimeFrom;

    /**
     * 范围查找订单创建时间的右边界
     */
    private LocalDateTime createTimeTo;

    /**
     * 排序字段
     */
    @OrderFieldEnum({OrderField.BALANCE, OrderField.PAY_TIME, OrderField.CREATE_TIME})
    private String orderBy = OrderField.CREATE_TIME;

    /**
     * 支付方式id
     */
    private Long payMethodId;

    /**
     * 范围查找支付时间的左边界
     */
    private LocalDateTime payTimeFrom;

    /**
     * 范围查找支付时间的右边界
     */
    private LocalDateTime payTimeTo;

    /**
     * 订单状态。1待支付 2已支付 3已取消
     */
    private Long status;

    /**
     * 用户id
     */
    private Long userId;
}
