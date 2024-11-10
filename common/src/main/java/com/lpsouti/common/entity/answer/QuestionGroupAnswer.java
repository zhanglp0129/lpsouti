package com.lpsouti.common.entity.answer;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

/**
 * 题组答案
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionGroupAnswer extends AnswerContent {
    /**
     * 子题目
     */
    @NotNull
    private List<AnswerContent> children;
}
