package com.lpsouti.admin.dto.user;

import com.lpsouti.common.annotation.OrderFieldEnum;
import com.lpsouti.common.dto.BasePageDTO;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.lpsouti.common.constant.OrderField.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageDTO extends BasePageDTO implements Serializable {
    private Byte role;

    private Byte status;

    private BigDecimal balanceFrom;

    private BigDecimal balanceTo;

    private BigDecimal freeBalanceFrom;

    private BigDecimal freeBalanceTo;

    private LocalDateTime createTimeFrom;

    private LocalDateTime createTimeTo;

    /**
     * 排序字段
     */
    @OrderFieldEnum({CREATE_TIME, BALANCE, FREE_BALANCE})
    private String orderBy = CREATE_TIME;
}
