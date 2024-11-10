package com.lpsouti.common.entity.answer;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 解答题答案
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EssayQuestionAnswer extends AnswerContent {
    /**
     * 答案内容，格式为answerFormat字段
     */
    @NotNull
    private String content;
}
