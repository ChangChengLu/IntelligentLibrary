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
public enum UserRoleEnum {

    /**
     * 用户角色：0:admin/1:normal_user/2:ban_user
     */
    ADMIN(0, "admin"),
    NORMAL_USER(1, "normal_user"),
    BAN_USER(2, "ban_user");

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
