package com.cclu.intellignetlibrary.annotation;

import com.cclu.intellignetlibrary.model.entity.User;
import com.cclu.intellignetlibrary.model.enums.UserRoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ChangCheng Lu
 * @date 2023/10/10 10:59
 * @description
 * @copyright 卢常诚 -- ChangChengLu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     * 必须有某个角色
     *
     * @return 判断用户角色是否匹配
     */
    UserRoleEnum mustRole() default UserRoleEnum.VIP1;

}
