package com.lpsouti.common.entity.answer;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

/**
 * 填空题答案
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FillBlankQuestionAnswer extends AnswerContent {
    /**
     * 填空题答案内容，可能会有多个空
     */
    @NotNull
    private List<String> blanks;
}
