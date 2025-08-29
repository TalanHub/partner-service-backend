package com.zjl.partnerservice.common;

import lombok.Data;

import java.io.Serializable;


/**
 * 通用返回类
 * @param <T>
 *
 */
@Data
public class BaseResponse<T> implements Serializable {

    // 实现Serializable需定义serialVersionUID
    private static final long serialVersionUID = 1L;


    private int code;

    private T data;

    private String message;

    private String description;


    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data, String message) {
        this(code, data, message, "");
    }


//    public BaseResponse(int code, T data) {
//        this(code, );
//    }
//



    public BaseResponse(ErrorCode errorCode, T data) {
        this(errorCode.getCode(), data, errorCode.getMessage(), errorCode.getDescription());
    }


    public BaseResponse(ErrorCode errorCode) {
        this(errorCode, null);
//        this(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }

}
