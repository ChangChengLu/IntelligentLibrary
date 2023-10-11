package com.cclu.intellignetlibrary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intellignetlibrary.common.response.BaseResponseCode;
import com.cclu.intellignetlibrary.constant.CommonConstant;
import com.cclu.intellignetlibrary.constant.UserConstant;
import com.cclu.intellignetlibrary.exception.BusinessException;
import com.cclu.intellignetlibrary.mapper.UserMapper;
import com.cclu.intellignetlibrary.model.entity.User;
import com.cclu.intellignetlibrary.model.enums.UserRoleEnum;
import com.cclu.intellignetlibrary.model.req.user.UserQueryRequest;
import com.cclu.intellignetlibrary.model.vo.user.LoginUserVO;
import com.cclu.intellignetlibrary.model.vo.user.UserVO;
import com.cclu.intellignetlibrary.service.UserService;
import com.cclu.intellignetlibrary.utils.SqlUtils;
import com.cclu.intellignetlibrary.utils.ThrowUtils;
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
 * @author ChangCheng Lu
 * @date 2023/9/9 13:46
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

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
        String unionId = userQueryRequest.getUnionId();
        String mpOpenId = userQueryRequest.getMpOpenId();
        String userName = userQueryRequest.getUserName();
        String userProfile = userQueryRequest.getUserProfile();
        String userRole = userQueryRequest.getUserRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(StringUtils.isNotBlank(unionId), "unionId", unionId);
        queryWrapper.eq(StringUtils.isNotBlank(mpOpenId), "mpOpenId", mpOpenId);
        queryWrapper.eq(StringUtils.isNotBlank(userRole), "userRole", userRole);
        queryWrapper.like(StringUtils.isNotBlank(userProfile), "userProfile", userProfile);
        queryWrapper.like(StringUtils.isNotBlank(userName), "userName", userName);
        queryWrapper.orderBy(
                SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

}
