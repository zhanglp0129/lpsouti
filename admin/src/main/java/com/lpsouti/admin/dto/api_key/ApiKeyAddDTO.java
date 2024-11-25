package com.lpsouti.admin.dto.api_key;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiKeyAddDTO implements Serializable {
    /**
     * 过期时间，null表示永不过期
     */
    private LocalDateTime expireTime;

    /**
     * 是否启用
     */
    @NotNull
    private Boolean isEnabled;

    /**
     * 备注
     */
    private String note;

    /**
     * 是否仅限免费接口
     */
    @NotNull
    private Boolean onlyFree;

    /**
     * 用户id，一个用户可以有多个api key
     */
    @NotNull
    private Long userId;
}
