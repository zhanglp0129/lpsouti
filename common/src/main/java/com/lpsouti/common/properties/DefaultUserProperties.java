package com.lpsouti.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "lpsouti.default-user")
@Data
public class DefaultUserProperties {
    private String username;
    private String email;
    private String password;
}
