package com.lpsouti.common.entity.answer;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 单选题答案
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleChoiceQuestionAnswer extends AnswerContent {
    @NotNull
    private Option option;
}
