package com.cclu.intelligentlibrary.exception;

import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import lombok.Getter;

/**
 * @author ChangCheng Lu
 * @date 2023/9/9 14:12
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(BaseResponseCode baseResponseCode) {
        super(baseResponseCode.getMessage());
        this.code = baseResponseCode.getCode();
    }

    public BusinessException(BaseResponseCode baseResponseCode, String message) {
        super(message);
        this.code = baseResponseCode.getCode();
    }

}
