package com.cclu.intellignetlibrary.common.response;

import lombok.Getter;

/**
 * @author ChangCheng Lu
 * @date 2023/9/9 14:04
 */
@Getter
public enum BaseResponseCode {
    /**
     * status code
     */
    SUCCESS(0, "ok"),
    PARAMS_ERROR(400, "请求参数错误"),
    TOO_MANY_REQUEST(429, "请求过于频繁"),
    NOT_LOGIN_ERROR(401, "未登录"),
    NO_AUTH_ERROR(401, "无权限"),
    NOT_FOUND_ERROR(404, "请求数据不存在"),
    FORBIDDEN_ERROR(403, "禁止访问"),
    SYSTEM_ERROR(500, "系统内部异常"),
    OPERATION_ERROR(501, "操作失败");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    BaseResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
