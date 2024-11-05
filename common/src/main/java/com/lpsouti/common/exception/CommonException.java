package com.lpsouti.common.exception;

import com.lpsouti.common.constant.ErrorCode;
import lombok.Getter;

/**
 * 业务逻辑通用异常
 */
@Getter
public class CommonException extends RuntimeException {
    private final int code;

    public CommonException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public CommonException(String msg) {
        this(ErrorCode.COMMON_ERROR_CODE, msg);
    }

    public CommonException(int code, Exception e) {
        super(e);
        this.code = code;
    }

    public CommonException(Exception e) {
        this(ErrorCode.COMMON_ERROR_CODE, e);
    }
}
