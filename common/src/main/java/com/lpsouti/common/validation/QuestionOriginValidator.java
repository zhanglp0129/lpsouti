package com.lpsouti.common.validation;

import cn.hutool.core.util.ArrayUtil;
import com.lpsouti.common.annotation.QuestionOriginEnum;
import com.lpsouti.common.constant.QuestionOrigin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class QuestionOriginValidator implements ConstraintValidator<QuestionOriginEnum, Byte> {
    @Override
    public boolean isValid(Byte origin, ConstraintValidatorContext constraintValidatorContext) {
        return origin == null || ArrayUtil.contains(QuestionOrigin.QUESTION_ORIGINS, origin);
    }
}
