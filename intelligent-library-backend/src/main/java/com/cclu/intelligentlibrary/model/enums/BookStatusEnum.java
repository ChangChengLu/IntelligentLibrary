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
public enum BookStatusEnum {

    /**
     * 书籍状态 book_state
     *
     * - 未上架 shelving
     * - 审核中 auditing
     * - 已上架 shelved
     * - 已下架 removed
     */
    SHELVING(0, "shelving"),
    AUDITING(1, "auditing"),
    SHELVED(2, "shelved"),
    REMOVED(3, "removed");

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
