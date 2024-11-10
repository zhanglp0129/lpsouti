package com.lpsouti.common.entity.answer;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 判断题答案
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrueFalseQuestionAnswer extends AnswerContent {
    /**
     * 判断题答案，true为对，false为错
     */
    @NotNull
    private Boolean answer;
}
