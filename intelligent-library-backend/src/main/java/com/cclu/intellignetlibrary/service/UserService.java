package com.cclu.intellignetlibrary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cclu.intellignetlibrary.model.entity.User;
import com.cclu.intellignetlibrary.model.vo.LoginUserVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ChangCheng Lu
 * @date 2023/9/9 13:43
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request 登录请求
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取脱敏的已登录用户信息
     *
     * @param user 未脱敏用户
     * @return 脱敏用户
     */
    LoginUserVO getLoginUserVO(User user);

}
