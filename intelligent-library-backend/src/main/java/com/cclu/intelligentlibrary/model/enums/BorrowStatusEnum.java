package com.cclu.intelligentlibrary.model.enums;

import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ChangCheng Lu
 * @date 2023/10/27 16:53
 * @description 用户状态枚举类
 * @copyright ChangChengLu
 */
@Getter
@ToString
@AllArgsConstructor
public enum BorrowStatusEnum {

    /**
     * 书籍借阅状态 borrow_state
     *
     * - 借阅中 borrowing
     * - 已归还 returned
     */
    BORROWING(0, "borrowing"),
    RETURNED(1, "returned");

    private final Integer code;

    private final String info;

    public static List<String> getInfos() {
        return Arrays.stream(values()).map(BorrowStatusEnum::getInfo).collect(Collectors.toList());
    }

    public static BorrowStatusEnum getEnumByCode(Integer code) {
        if (code == null) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR);
        }
        for (BorrowStatusEnum userRoleEnum : values()) {
            if (Objects.equals(userRoleEnum.getCode(), code)) {
                return userRoleEnum;
            }
        }
        return null;
    }
}
