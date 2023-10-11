package com.cclu.intellignetlibrary.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cclu.intellignetlibrary.model.entity.User;
import com.cclu.intellignetlibrary.model.req.user.UserQueryRequest;
import com.cclu.intellignetlibrary.model.vo.user.LoginUserVO;
import com.cclu.intellignetlibrary.model.vo.user.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 用户注销
     *
     * @param request 注销请求
     * @return 注销是否成功
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request 用户请求
     * @return 当前登录用户
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 判断当前用户是否为管理员
     *
     * @param request 用户请求
     * @return 当前用户是否为管理员
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 判断用户是否为管理员
     *
     * @param user 任意用户
     * @return 判断结果
     */
    boolean isAdmin(User user);

    /**
     * 获取脱敏的用户信息
     *
     * @param user 未脱敏用户信息
     * @return 脱敏的用户信息
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏的用户信息
     *
     * @param userList 未脱敏用户信息列表
     * @return 托名用户细信息列表
     */
    List<UserVO> getUserVO(List<User> userList);

    /**
     * 获取查询条件
     *
     * @param userQueryRequest 用户查询请求
     * @return 查询用户
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

}
