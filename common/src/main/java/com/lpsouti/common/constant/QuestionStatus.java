package com.lpsouti.common.constant;

public class QuestionStatus {
    // 待审核
    public static final Byte PENDING_AUDIT = 1;
    // 已通过
    public static final Byte ACCEPT = 2;
    // 已驳回
    public static final Byte REJECT = 3;

    public static final Byte[] QUESTION_STATUS = {1, 2, 3};
}
