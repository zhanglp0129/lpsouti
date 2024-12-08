package com.lpsouti.common.entity.answer;

import com.lpsouti.common.annotation.QuestionTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 答案内容
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AnswerContent {
    /**
     * 题目类型，1选择题 2多选题 3判断题 4填空题 5解答题 6题组
     */
    @QuestionTypeEnum
    @NotNull
    protected Byte type;
}
