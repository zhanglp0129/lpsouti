package com.lpsouti.common.validation;

import cn.hutool.core.util.ArrayUtil;
import com.lpsouti.common.annotation.RequestMethodEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RequestMethodValidator implements ConstraintValidator<RequestMethodEnum, String> {
    private static final String[] methods = {"GET", "POST", "PUT", "DELETE", "PATCH"};

    @Override
    public boolean isValid(String method, ConstraintValidatorContext constraintValidatorContext) {
        log.debug("request method = {}", method);
        return method == null || ArrayUtil.contains(methods, method);
    }
}
