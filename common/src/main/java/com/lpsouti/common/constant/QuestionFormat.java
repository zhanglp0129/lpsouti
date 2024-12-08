package com.lpsouti.common.constant;

// 题目格式
public class QuestionFormat {
    // 纯文本
    public static final Byte PLAIN_TEXT = 1;
    // 图片
    public static final Byte IMAGE = 2;
    // Latex公式
    public static final Byte LATEX = 3;
    // 富文本
    public static final Byte RICH_TEXT = 4;

    // 可选的题目格式
    public static final Byte[] QUESTION_FORMATS = {1, 2, 3, 4};
}
