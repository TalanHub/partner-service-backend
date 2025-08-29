package com.zjl.partnerservice.common;

public enum ErrorCode {



    // 全局错误码
    SUCCESS(0,"ok", ""),
    PARAMS_ERROR(40001, "请求参数错误", ""),
    NOT_FOUND(40002, "找不到你所请求请求的数据",""),
//    PARAMS_NULL(40002, "请求参数为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", ""),
    SYSTEM_ERROR(50000, "系统内部异常", ""),





    // 用户相关错误码
    USER_NOT_EXIST(10000, "用户不存在", ""),
    USER_EXIST(10001, "用户已存在", ""),
    USER_ACCOUNT_ERROR(10002, "用户名或密码错误", ""),
    USER_PASSWORD_ERROR(10003, "密码错误", ""),
    USER_STATUS_ERROR(10004, "用户状态异常", "");






    /**
     * 状态码
     */
    public final int code;

    /**
     * 状态码信息
     */
    public final String message;

    /**
     * 错误描述（详情）
     */
    public final String description;


    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
