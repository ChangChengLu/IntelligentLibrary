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
public enum VipRoleEnum {

    /**
     * 会员类型 vip_type
     *
     * - 普通会员 ordinary 0
     * - 金牌会员 gold 1
     * - 白金会员 platinum 2
     */
    ORDINARY(0, "ordinary_number"),
    GOLD_NUMBER(1, "gold_number"),
    PLATINUM_NUMBER(2, "platinum_number");

    private final Integer code;

    private final String info;

    public static List<String> getInfos() {
        return Arrays.stream(values()).map(VipRoleEnum::getInfo).collect(Collectors.toList());
    }

    public static VipRoleEnum getEnumByCode(Integer code) {
        if (code == null) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR);
        }
        for (VipRoleEnum userRoleEnum : values()) {
            if (Objects.equals(userRoleEnum.getCode(), code)) {
                return userRoleEnum;
            }
        }
        return null;
    }
}
