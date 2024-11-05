package com.lpsouti.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {
    private int code;
    private T data;
    private String msg;

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(1000, data, null);
    }

    public static ResultVO<Object> success() {
        return new ResultVO<>(1000, null, null);
    }

    public static ResultVO<Object> error(String msg) {
        return new ResultVO<>(2000, null, msg);
    }

    public static ResultVO<Object> error(int code, String msg) {
        return new ResultVO<>(code, null, msg);
    }
}
