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
public enum OrderStatusEnum {

    /**
     * 订单状态 order_state
     *
     * - 未付款 paying
     * - 已付款 payed
     */
    PAYING(0, "paying"),
    PAYED(1, "payed");

    private final Integer code;

    private final String info;

    public static List<String> getInfos() {
        return Arrays.stream(values()).map(OrderStatusEnum::getInfo).collect(Collectors.toList());
    }

    public static OrderStatusEnum getEnumByCode(Integer code) {
        if (code == null) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR);
        }
        for (OrderStatusEnum userRoleEnum : values()) {
            if (Objects.equals(userRoleEnum.getCode(), code)) {
                return userRoleEnum;
            }
        }
        return null;
    }
}
