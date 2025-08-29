package com.zjl.partnerservice.common;

public final class StdResponse {

    private StdResponse() {
        throw new UnsupportedOperationException("这是一个工具类，不能被实例化。");
    }


    /**
     * 成功（仅数据）
     * @param data
     * @return
     * @param <T>
     */
    public static <T> BaseResponse<T> success(T data) {
//        return new BaseResponse<>(0, data, "ok");
        return new BaseResponse<>(ErrorCode.SUCCESS, data);
    }


//    public static <T> BaseResponse<T> fail(String message) {
//        return new BaseResponse<>(-1, null, "");
//    }


//    public static BaseResponse error(int code, String message, String description) {
//        return new BaseResponse(code, message, description, null);
//    }


    public static <T> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }


    public static <T> BaseResponse<T> error(ErrorCode errorCode, String description) {
        return new BaseResponse<>(errorCode.getCode(),null, errorCode.getMessage(), description);
    }

    // TODO: 必须要改，因为data是泛型，很容易不小心把message或是description当成data传进去

    public static <T> BaseResponse<T> error(int code, String message, String description) {
        return new BaseResponse<>(code,null, message, description);
    }

    public static BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse(errorCode.getCode(), null, message, description);
    }



}
