package com.lpsouti.admin.dto.order;

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
public class OrderEditDTO implements Serializable {
    /**
     * 支付金额
     */
    private BigDecimal amount;

    /**
     * 到账余额
     */
    private BigDecimal balance;

    /**
     * 订单id
     */
    @NotNull
    private Long id;

    /**
     * 支付方式id
     */
    private Long payMethodId;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 订单状态，1待支付 2已支付 3已取消
     */
    private Byte status;

    /**
     * 用户id
     */
    private Long userId;
}
