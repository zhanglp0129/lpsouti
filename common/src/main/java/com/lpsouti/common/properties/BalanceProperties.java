package com.lpsouti.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@ConfigurationProperties(prefix = "lpsouti.balance")
@Data
public class BalanceProperties {
    /**
     * 每日免费余额
     */
    private BigDecimal dailyFreeBalance;
}
