package com.lpsouti.common.entity.answer;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

/**
 * 多选题答案
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultipleChoiceQuestionAnswer extends AnswerContent {
    @NotNull
    private List<Option> options;
}
