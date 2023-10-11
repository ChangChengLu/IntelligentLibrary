package com.cclu.intellignetlibrary.model.enums;

import com.cclu.intellignetlibrary.common.response.BaseResponseCode;
import com.cclu.intellignetlibrary.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ChangCheng Lu
 * @date 2023/10/10 10:44
 * @description
 * @copyright 卢常诚 -- ChangChengLu
 */
@Getter
@ToString
@AllArgsConstructor
public enum BookBorrowStatusEnum {

    /**
     * 0:borrowing/1:borrowed
     */
    BORROWING(0, "borrowing"),
    BORROWED(1, "borrowed");

    private final Integer code;

    private final String info;

    public static List<String> getInfos() {
        return Arrays.stream(values()).map(BookBorrowStatusEnum::getInfo).collect(Collectors.toList());
    }

    public static BookBorrowStatusEnum getEnumByCode(Integer code) {
        if (code == null) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR);
        }
        for (BookBorrowStatusEnum userRoleEnum : values()) {
            if (Objects.equals(userRoleEnum.getCode(), code)) {
                return userRoleEnum;
            }
        }
        return null;
    }
}
