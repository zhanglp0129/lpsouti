package com.lpsouti.common.handler;

import com.lpsouti.common.exception.CommonException;
import com.lpsouti.common.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static com.lpsouti.common.constant.ErrorCode.BIND_ERROR_CODE;
import static com.lpsouti.common.constant.ErrorCode.SQL_ERROR_CODE;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public Object handleCommonException(CommonException e) {
        log.info("出现异常{}：{}", e.getCode(), e.getMessage());
        return processResponse(e);
    }

    @ExceptionHandler(DataAccessException.class)
    public Object handleSQLException(DataAccessException e) {
        log.info("数据库异常：{}", e.getMessage());
        String msg = e.getRootCause() != null ? e.getRootCause().getMessage() : "数据库操作失败";
        return processResponse(new CommonException(SQL_ERROR_CODE, msg));
    }

    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException e) {
        log.info("参数绑定异常：{}", e.getMessage());
        String msg = e.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return processResponse(new CommonException(BIND_ERROR_CODE, msg));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object handleBindException(HttpMessageNotReadableException e) {
        log.info("请求体格式错误：{}", e.getMessage());
        String msg = e.getCause() != null ? e.getCause().getMessage() : "请求体格式错误";
        return processResponse(new CommonException(BIND_ERROR_CODE, msg));
    }

    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        log.error("服务器内部异常：{}", e.getMessage());
        log.debug("完整异常信息：", e);
        return processResponse(e);
    }

    // 处理异常响应
    private ResponseEntity<Object> processResponse(Exception e) {
        if (e instanceof CommonException me) {
            // 业务逻辑异常
            return ResponseEntity.status(400).body(ResultVO.error(me.getCode(), me.getMessage()));
        }
        // 其他异常
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
