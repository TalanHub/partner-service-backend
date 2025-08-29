package com.zjl.partnerservice.exception;


import com.zjl.partnerservice.common.BaseResponse;
import com.zjl.partnerservice.common.ErrorCode;
import com.zjl.partnerservice.common.StdResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BussinessException.class)
    public BaseResponse<?> bussinessExceptionHandler(BussinessException e) {
        log.error("BussinessException: " + e.getMessage());
        return StdResponse.error(e.getCode(), e.getMessage(), "");
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(BussinessException e) {
        log.error("RuntimeException: ", e);
        return StdResponse.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
    }

}


