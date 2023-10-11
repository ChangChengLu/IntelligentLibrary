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
public enum BookStatusEnum {

    /**
     * 书籍状态：0未上架/1已上架/2已下架
     */
    NO_TAKEN_ON(0, "no_take_on"),
    TAKEN_ON(1, "take_on"),
    TAKE_DOWN(2, "take_down");

    private final Integer code;

    private final String info;

    public static List<String> getInfos() {
        return Arrays.stream(values()).map(BookStatusEnum::getInfo).collect(Collectors.toList());
    }

    public static BookStatusEnum getEnumByCode(Integer code) {
        if (code == null) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR);
        }
        for (BookStatusEnum userRoleEnum : values()) {
            if (Objects.equals(userRoleEnum.getCode(), code)) {
                return userRoleEnum;
            }
        }
        return null;
    }
}
