package com.lpsouti.common.validation;

import com.lpsouti.common.annotation.OrderFieldEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderFieldValidator implements ConstraintValidator<OrderFieldEnum, String> {

    private String[] fields;

    @Override
    public void initialize(OrderFieldEnum constraintAnnotation) {
        log.debug("order field = {}", (Object) fields);
        fields = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String field : fields) {
            if (field.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
