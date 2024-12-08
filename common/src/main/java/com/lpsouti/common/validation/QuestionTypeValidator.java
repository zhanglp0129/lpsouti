package com.lpsouti.common.validation;

import cn.hutool.core.util.ArrayUtil;
import com.lpsouti.common.annotation.QuestionTypeEnum;
import com.lpsouti.common.constant.QuestionType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QuestionTypeValidator implements ConstraintValidator<QuestionTypeEnum, Byte> {
    @Override
    public boolean isValid(Byte type, ConstraintValidatorContext constraintValidatorContext) {
        return type == null || ArrayUtil.contains(QuestionType.QUESTION_TYPES, type);
    }
}
