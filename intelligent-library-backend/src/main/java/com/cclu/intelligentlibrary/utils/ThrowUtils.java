package com.cclu.intelligentlibrary.utils;

import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.exception.BusinessException;

/**
 * @author ChangCheng Lu
 * @date 2023/9/9 14:01
 */
public class ThrowUtils {

    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    public static void throwIf(boolean condition, BaseResponseCode baseResponseCode) {
        throwIf(condition, new BusinessException(baseResponseCode));
    }

    public static void throwIf(boolean condition, BaseResponseCode baseResponseCode, String message) {
        throwIf(condition, new BusinessException(baseResponseCode, message));
    }

}
