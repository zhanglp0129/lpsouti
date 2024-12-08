package com.lpsouti.common.annotation;

import com.lpsouti.common.validation.QuestionOriginValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义注解，用于判断题目来源是否正确
 */
@Documented
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {QuestionOriginValidator.class})
public @interface QuestionOriginEnum {
    String message() default "题目来源错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
