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
public enum BookOrderStatusEnum {

    /**
     * 0:paying/1:payed
     */
    PAYING(0, "paying"),
    PAYED(1, "payed");

    private final Integer code;

    private final String info;

    public static List<String> getInfos() {
        return Arrays.stream(values()).map(BookOrderStatusEnum::getInfo).collect(Collectors.toList());
    }

    public static BookOrderStatusEnum getEnumByCode(Integer code) {
        if (code == null) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR);
        }
        for (BookOrderStatusEnum userRoleEnum : values()) {
            if (Objects.equals(userRoleEnum.getCode(), code)) {
                return userRoleEnum;
            }
        }
        return null;
    }
}
