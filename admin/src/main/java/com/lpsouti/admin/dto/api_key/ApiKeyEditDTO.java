package com.lpsouti.admin.dto.api_key;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiKeyEditDTO implements Serializable {
    /**
     * 过期时间，null表示永不过期
     */
    private LocalDateTime expireTime;

    /**
     * 用户api key id
     */
    @NotNull
    private Long id;

    /**
     * 是否启用
     */
    private Boolean isEnabled;

    /**
     * 备注
     */
    @Length(max = 100)
    private String note;

    /**
     * 是否仅限免费接口
     */
    private Boolean onlyFree;

    /**
     * 密钥id，一个32字节、36进制整数
     */
    @Length(max = 50)
    private String secretId;

    /**
     * 密钥明文
     */
    @Length(max = 50)
    private String secretKey;
}
