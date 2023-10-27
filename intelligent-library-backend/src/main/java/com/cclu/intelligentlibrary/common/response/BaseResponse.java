package com.cclu.intelligentlibrary.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/9/9 14:00
 */
@Data
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(BaseResponseCode baseResponseCode) {
        this(baseResponseCode.getCode(), null, baseResponseCode.getMessage());
    }

}
