package com.cclu.intellignetlibrary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intellignetlibrary.common.response.BaseResponseCode;
import com.cclu.intellignetlibrary.constant.UserConstant;
import com.cclu.intellignetlibrary.exception.BusinessException;
import com.cclu.intellignetlibrary.mapper.UserMapper;
import com.cclu.intellignetlibrary.model.entity.User;
import com.cclu.intellignetlibrary.model.vo.LoginUserVO;
import com.cclu.intellignetlibrary.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < USER_ACCOUNT_MIN_LENGTH) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR, "账号长度过短");
        }
        if (userPassword.length() < USER_PASSWORD_MIN_LENGTH || checkPassword.length() < USER_PASSWORD_MIN_LENGTH) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR, "密码长度过短");
        }
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR, "两次输入密码不一致");
        }
        synchronized (userAccount.intern()) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_account", userAccount);
            if (userMapper.selectOne(queryWrapper) != null) {
                throw new BusinessException(BaseResponseCode.PARAMS_ERROR, "账户重复");
            }
            String encryptPassword = DigestUtils.md5DigestAsHex((UserConstant.USER_SALT + userPassword).getBytes());
            User user = new User();
            user.setUserAccount(userAccount);
            user.setUserPassword(encryptPassword);
            boolean saveResult = this.save(user);
            if (!saveResult) {
                throw new BusinessException(BaseResponseCode.SYSTEM_ERROR, "注册失败，数据库错误");
            }
            return user.getId();
        }
    }

    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < USER_ACCOUNT_MIN_LENGTH) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR, "账号错误");
        }
        if (userPassword.length() < USER_PASSWORD_MIN_LENGTH) {
            throw new BusinessException(BaseResponseCode.PARAMS_ERROR, "密码错误");
        }
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
}
