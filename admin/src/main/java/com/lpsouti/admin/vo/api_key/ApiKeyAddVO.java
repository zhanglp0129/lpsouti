package com.lpsouti.admin.vo.api_key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiKeyAddVO implements Serializable {
    /**
     * 密钥id
     */
    private String secretId;

    /**
     * 密钥，仅在创建时允许查看
     */
    private String secretKey;
}
