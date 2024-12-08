package com.lpsouti.admin.dto.api;

import com.lpsouti.common.annotation.RequestMethodEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiEditDTO implements Serializable {
    /**
     * api接口id
     */
    @NotNull
    private Long id;

    /**
     * 每次调用消耗余额
     */
    private BigDecimal consumeBalance;

    /**
     * 接口描述，markdown格式，会展示在接口文档中
     */
    private String description;

    /**
     * 是否为免费接口
     */
    private Boolean isFree;

    /**
     * 是否可用
     */
    private Boolean isEnabled;

    /**
     * 请求方式
     */
    @RequestMethodEnum
    private String method;

    /**
     * 接口名称
     */
    @Length(max = 20)
    private String name;

    /**
     * 请求路径，不能使用路径参数
     */
    @Length(max = 100)
    private String path;
}
