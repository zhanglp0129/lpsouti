package com.lpsouti.admin.dto.order;

import com.lpsouti.common.constant.OrderStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddDTO implements Serializable {
    /**
     * 支付金额
     */
    @NotNull
    private BigDecimal amount;

    /**
     * 到账余额
     */
    @NotNull
    private BigDecimal balance;

    /**
     * 支付方式id
     */
    @NotNull
    private Long payMethodId;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 订单状态，1待支付 2已支付 3已取消。默认为待支付
     */
    @NotNull
    @Min(1)
    @Max(3)
    private Byte status = OrderStatus.PENDING_PAYMENT;

    /**
     * 用户id
     */
    @NotNull
    private Long userId;
}
