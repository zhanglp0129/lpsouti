package com.lpsouti.admin.dto.api_record;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRecordEditDTO implements Serializable {
    /**
     * api id
     */
    private Long apiId;

    /**
     * 消耗余额
     */
    private BigDecimal consumeBalance;

    /**
     * 调用错误信息，null表示调用成功
     */
    private String err;

    /**
     * api调用记录id
     */
    @NotNull
    private Long id;

    /**
     * 调用者ip
     */
    @Length(max = 45)
    private String ip;

    /**
     * api key id
     */
    private Long keyId;

    /**
     * 是否改为调用成功，为true时，err必须为null
     */
    private Boolean success;
}
