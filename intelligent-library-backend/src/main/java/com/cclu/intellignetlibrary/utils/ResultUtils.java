package com.cclu.intellignetlibrary.utils;

import com.cclu.intellignetlibrary.common.response.BaseResponse;
import com.cclu.intellignetlibrary.common.response.BaseResponseCode;

/**
 * @author ChangCheng Lu
 * @date 2023/9/9 14:18
 */
public class ResultUtils {
    
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }
    
    public static BaseResponse<?> error(BaseResponseCode baseResponseCode) {
        return new BaseResponse<>(baseResponseCode);
    }
    
    public static BaseResponse<?> error(int code, String message) {
        return new BaseResponse<>(code, null, message);
    }
    
    public static BaseResponse<?> error(BaseResponseCode baseResponseCode, String message) {
        return new BaseResponse<>(baseResponseCode.getCode(), null, message);
    }

}
