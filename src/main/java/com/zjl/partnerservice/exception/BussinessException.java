package com.zjl.partnerservice.exception;


import com.zjl.partnerservice.common.ErrorCode;


/**
 * 业务异常类
 */
public class BussinessException extends RuntimeException {


    private final int code;

//    private final String description;

    public BussinessException(String message, int code) {
        super(message);
        this.code = code;
    }


    public BussinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BussinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }


    /**
     * 获取错误码
     * @return 错误码
     *
     * 注意：异常消息请使用继承自父类的getMessage()方法获取
     */
    public int getCode() {
        return code;
    }



}
