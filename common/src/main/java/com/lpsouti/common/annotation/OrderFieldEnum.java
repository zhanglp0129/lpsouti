package com.lpsouti.common.annotation;

import com.lpsouti.common.validation.OrderFieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义注解，用于判断排序字段是否符合规定
 */
@Documented
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {OrderFieldValidator.class})
public @interface OrderFieldEnum {
    String message() default "排序字段错误";

    String[] value();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
