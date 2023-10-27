package com.cclu.intelligentlibrary.annotation;

import com.cclu.intelligentlibrary.model.enums.UserRoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ChangCheng Lu
 * @date 2023/10/10 10:59
 * @description
 * @copyright ChangChengLu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     * 必须有某个角色
     *
     * @return 判断用户角色是否匹配
     */
    UserRoleEnum mustRole() default UserRoleEnum.NORMAL_USER;

}
