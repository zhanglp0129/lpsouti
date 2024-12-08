package com.lpsouti.common.constant;

// 题目类型
public class QuestionType {
    // 单选题
    public static final Byte SINGLE_CHOICE_QUESTION = 1;
    // 多选题
    public static final Byte MULTIPLE_CHOICE_QUESTION = 2;
    // 判断题
    public static final Byte TRUE_FALSE_QUESTION = 3;
    // 填空题
    public static final Byte FILL_BLANK_QUESTION = 4;
    // 解答题
    public static final Byte ESSAY_QUESTION = 5;
    // 题组
    public static final Byte QUESTION_GROUP = 6;

    // 可用的类型
    public static final Byte[] QUESTION_TYPES = {1, 2, 3, 4, 5, 6};
}
