package com.lpsouti.common.validation;

import com.lpsouti.common.annotation.OrderFieldEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderFieldValidator implements ConstraintValidator<OrderFieldEnum, String> {

    private String[] fields;

    @Override
    public void initialize(OrderFieldEnum constraintAnnotation) {
        log.debug("available order fields = {}", (Object) fields);
        fields = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        log.debug("order field = {}", value);
        for (String field : fields) {
            if (field.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
