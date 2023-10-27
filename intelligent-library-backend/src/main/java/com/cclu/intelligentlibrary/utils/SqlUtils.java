package com.cclu.intelligentlibrary.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ChangCheng Lu
 * @date 2023/10/10 16:18
 * @description
 * @copyright ChangChengLu
 */
public class SqlUtils {

    /**
     * 校验排序字段是否合法（防止 SQL 注入）
     *
     * @param sortField 排序字段
     * @return 字段合法性
     */
    public static boolean validSortField(String sortField) {
        if (StringUtils.isBlank(sortField)) {
            return false;
        }
        return !StringUtils.containsAny(sortField, "=", "(", ")", " ");
    }

}
