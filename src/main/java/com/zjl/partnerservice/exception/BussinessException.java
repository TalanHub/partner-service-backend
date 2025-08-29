package com.zjl.partnerservice.exception;


import com.zjl.partnerservice.common.ErrorCode;

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



    public int getCode() {
        return code;
    }

}
