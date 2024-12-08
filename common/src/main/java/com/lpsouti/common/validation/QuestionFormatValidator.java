package com.lpsouti.common.validation;

import cn.hutool.core.util.ArrayUtil;
import com.lpsouti.common.annotation.QuestionFormatEnum;
import com.lpsouti.common.constant.QuestionFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QuestionFormatValidator implements ConstraintValidator<QuestionFormatEnum, Byte> {
    @Override
    public boolean isValid(Byte format, ConstraintValidatorContext constraintValidatorContext) {
        return format == null || ArrayUtil.contains(QuestionFormat.QUESTION_FORMATS, format);
    }
}
