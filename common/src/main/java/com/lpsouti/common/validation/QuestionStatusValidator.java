package com.lpsouti.common.validation;

import cn.hutool.core.util.ArrayUtil;
import com.lpsouti.common.annotation.QuestionStatusEnum;
import com.lpsouti.common.constant.QuestionStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class QuestionStatusValidator implements ConstraintValidator<QuestionStatusEnum, Byte> {
    @Override
    public boolean isValid(Byte status, ConstraintValidatorContext constraintValidatorContext) {
        return status == null || ArrayUtil.contains(QuestionStatus.QUESTION_STATUS, status);
    }
}
