package com.lpsouti.common.constant;

import java.util.Locale;

// 时间相关常量
public class DateConstant {
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_TIME_ZONE = "Asia/Shanghai";
    public static final Locale DEFAULT_LOCALE = Locale.of("zh", "CN");

    private DateConstant() {
    }
}
