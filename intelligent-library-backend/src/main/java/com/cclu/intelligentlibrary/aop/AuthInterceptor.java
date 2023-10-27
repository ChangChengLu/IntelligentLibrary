package com.cclu.intelligentlibrary.aop;

import com.cclu.intelligentlibrary.annotation.AuthCheck;
import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.exception.BusinessException;
import com.cclu.intelligentlibrary.model.entity.User;
import com.cclu.intelligentlibrary.model.enums.UserRoleEnum;
import com.cclu.intelligentlibrary.service.UserService;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ChangCheng Lu
 * @date 2023/10/10 11:00
 * @description
 * @copyright ChangChengLu
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     *
     * @param joinPoint 切入点
     * @param authCheck 角色核实注释
     * @return 下一个拦截器
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        UserRoleEnum mustUserRoleEnum = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByCode(loginUser.getUserRole());
        ThrowUtils.throwIf(
                userRoleEnum == null,
                new BusinessException(BaseResponseCode.PARAMS_ERROR, "用户角色不存在")
        );
        ThrowUtils.throwIf(
                UserRoleEnum.BAN_USER.getCode().equals(userRoleEnum.getCode()),
                new BusinessException(BaseResponseCode.NOT_FOUND_ERROR)
        );
        ThrowUtils.throwIf(
                mustUserRoleEnum.getCode().equals(userRoleEnum.getCode()),
                new BusinessException(BaseResponseCode.NOT_FOUND_ERROR)
        );
        return joinPoint.proceed();
    }
}
