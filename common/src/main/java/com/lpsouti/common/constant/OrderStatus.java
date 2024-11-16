package com.lpsouti.common.constant;

// 订单状态
public class OrderStatus {
    // 待支付
    public static final Byte PENDING_PAYMENT = 1;
    // 已支付
    public static final Byte PAID = 2;
    // 已取消
    public static final Byte CANCELLED = 3;

    private OrderStatus() {
    }
}
