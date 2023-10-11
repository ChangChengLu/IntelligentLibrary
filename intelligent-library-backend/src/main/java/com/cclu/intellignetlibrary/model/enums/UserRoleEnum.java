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
public enum UserRoleEnum {

    /**
     * 用户角色：0:admin/1:vip1/2:vip2/3:vip3/4:ban
     */
    ADMIN(0, "admin"),
    VIP1(1, "vip1"),
    VIP2(2, "vip2"),
    VIP3(3, "vip3"),
    BAN(4, "ban");

    private final Integer code;

    private final String info;

    public static List<String> getInfos() {
        return Arrays.stream(values()).map(UserRoleEnum::getInfo).collect(Collectors.toList());
    }

    public static UserRoleEnum getEnumByCode(Integer code) {
        if (code == null) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR);
        }
        for (UserRoleEnum userRoleEnum : values()) {
            if (Objects.equals(userRoleEnum.getCode(), code)) {
                return userRoleEnum;
            }
        }
        return null;
    }
}
