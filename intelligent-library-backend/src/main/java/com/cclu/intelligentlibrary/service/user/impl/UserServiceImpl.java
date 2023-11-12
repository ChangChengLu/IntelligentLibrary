package com.cclu.intelligentlibrary.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.constant.CommonConstant;
import com.cclu.intelligentlibrary.constant.UserConstant;
import com.cclu.intelligentlibrary.exception.BusinessException;
import com.cclu.intelligentlibrary.model.po.User;
import com.cclu.intelligentlibrary.model.enums.UserRoleEnum;
import com.cclu.intelligentlibrary.model.req.user.UserQueryRequest;
import com.cclu.intelligentlibrary.model.vo.user.LoginUserVO;
import com.cclu.intelligentlibrary.model.vo.user.UserVO;
import com.cclu.intelligentlibrary.service.user.UserService;
import com.cclu.intelligentlibrary.mapper.UserMapper;
import com.cclu.intelligentlibrary.utils.SqlUtils;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author ChangChengLu
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-10-27 16:47:15
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    private static final int USER_ACCOUNT_MIN_LENGTH = 5;

    private static final int USER_PASSWORD_MIN_LENGTH = 8;

    @Resource
    private UserMapper userMapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        ThrowUtils.throwIf(
                StringUtils.isAnyBlank(userAccount, userPassword, checkPassword),
                BaseResponseCode.PARAMS_ERROR, "参数为空"
        );
        ThrowUtils.throwIf(
                userAccount.length() < USER_ACCOUNT_MIN_LENGTH,
                BaseResponseCode.PARAMS_ERROR, "账号长度过短"
        );
        ThrowUtils.throwIf(
                userPassword.length() < USER_PASSWORD_MIN_LENGTH || checkPassword.length() < USER_PASSWORD_MIN_LENGTH,
                BaseResponseCode.PARAMS_ERROR, "密码长度过短"
        );
        ThrowUtils.throwIf(
                !userPassword.equals(checkPassword),
                BaseResponseCode.PARAMS_ERROR, "两次输入密码不一致"
        );
        synchronized (userAccount.intern()) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_account", userAccount);
            ThrowUtils.throwIf(
                    userMapper.selectOne(queryWrapper) != null,
                    BaseResponseCode.PARAMS_ERROR, "账户重复"
            );
            String encryptPassword = DigestUtils.md5DigestAsHex((UserConstant.USER_SALT + userPassword).getBytes());
            User user = new User();
            user.setUserAccount(userAccount);
            user.setUserPassword(encryptPassword);
            boolean saveResult = this.save(user);
            ThrowUtils.throwIf(!saveResult, BaseResponseCode.SYSTEM_ERROR, "注册失败，数据库错误");
            return user.getId();
        }
    }

    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        ThrowUtils.throwIf(
                StringUtils.isAnyBlank(userAccount, userPassword),
                BaseResponseCode.PARAMS_ERROR, "参数为空"
        );
        ThrowUtils.throwIf(
                userAccount.length() < USER_ACCOUNT_MIN_LENGTH,
                BaseResponseCode.PARAMS_ERROR, "账号长度过短"
        );
        ThrowUtils.throwIf(
                userPassword.length() < USER_PASSWORD_MIN_LENGTH,
                BaseResponseCode.PARAMS_ERROR, "密码长度过短"
        );
        String encryptPassword = DigestUtils.md5DigestAsHex((UserConstant.USER_SALT + userPassword).getBytes());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        queryWrapper.eq("user_password", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
        return getLoginUserVO(user);
    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        ThrowUtils.throwIf(
                request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE) == null,
                BaseResponseCode.OPERATION_ERROR, "未登录");
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        return true;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        ThrowUtils.throwIf(
                request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE) == null,
                BaseResponseCode.OPERATION_ERROR, "未登录");
        return (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        ThrowUtils.throwIf(
                request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE) == null,
                BaseResponseCode.OPERATION_ERROR, "未登录");
        User loginUser = (User) request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByCode(loginUser.getUserRole());
        ThrowUtils.throwIf(
                userRoleEnum == null,
                BaseResponseCode.PARAMS_ERROR, "用户角色不存在"
        );
        return isAdmin(loginUser);
    }

    @Override
    public boolean isAdmin(User user) {
        ThrowUtils.throwIf(user == null, BaseResponseCode.PARAMS_ERROR, "请求参数为空");
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByCode(user.getUserRole());
        ThrowUtils.throwIf(userRoleEnum == null, BaseResponseCode.PARAMS_ERROR, "用户角色不存在");
        return UserRoleEnum.ADMIN.getCode().equals(userRoleEnum.getCode());
    }

    @Override
    public UserVO getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<UserVO> getUserVO(List<User> userList) {
        if (CollectionUtils.isEmpty(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        ThrowUtils.throwIf(userQueryRequest == null, BaseResponseCode.PARAMS_ERROR, "请求参数为空");
        Long id = userQueryRequest.getId();
        String userName = userQueryRequest.getUserNickname();
        String userProfile = userQueryRequest.getUserDesc();
        Integer userRole = userQueryRequest.getUserRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(userRole != null, "user_role", userRole);
        queryWrapper.like(StringUtils.isNotBlank(userProfile), "user_desc", userProfile);
        queryWrapper.like(StringUtils.isNotBlank(userName), "user_nickname", userName);
        queryWrapper.orderBy(
                SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

}




