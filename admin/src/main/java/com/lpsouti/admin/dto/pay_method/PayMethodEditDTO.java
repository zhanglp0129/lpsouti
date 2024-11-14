package com.lpsouti.admin.dto.pay_method;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayMethodEditDTO {
    /**
     * 支付方式id
     */
    @NotNull
    private Long id;

    /**
     * 支付货币，用英文缩写
     */
    @Length(max = 10)
    private String currency = "CNY";

    /**
     * 易支付密钥
     */
    @Length(max = 200)
    private String epayKey;

    /**
     * 易支付商户id
     */
    private Long epayPid;

    /**
     * 易支付接口地址
     */
    @Length(max = 128)
    private String epayUrl;

    /**
     * 支付方式名称
     */
    @Length(max = 10)
    private String name;

    /**
     * 充值倍率，充值1单位货币可以获得多少余额
     */
    private BigDecimal rate = new BigDecimal(1);
}
