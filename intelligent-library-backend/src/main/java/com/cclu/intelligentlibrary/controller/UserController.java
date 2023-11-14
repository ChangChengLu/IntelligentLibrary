package com.cclu.intelligentlibrary.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cclu.intelligentlibrary.annotation.AuthCheck;
import com.cclu.intelligentlibrary.common.request.DeleteRequest;
import com.cclu.intelligentlibrary.common.response.BaseResponse;
import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.model.enums.UserRoleEnum;
import com.cclu.intelligentlibrary.model.po.User;
import com.cclu.intelligentlibrary.model.req.user.*;
import com.cclu.intelligentlibrary.model.vo.user.LoginUserVO;
import com.cclu.intelligentlibrary.model.vo.user.UserVO;
import com.cclu.intelligentlibrary.service.user.UserService;
import com.cclu.intelligentlibrary.utils.ResultUtils;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ChangCheng Lu
 * @date 2023/9/19 15:55
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/registry")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterReq userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, BaseResponseCode.PARAMS_ERROR);
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginReq userLoginRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(userLoginRequest == null, BaseResponseCode.PARAMS_ERROR);
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(loginUserVO);
    }

    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        ThrowUtils.throwIf(request == null, BaseResponseCode.PARAMS_ERROR);
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/get/login")
    public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        return ResultUtils.success(userService.getLoginUserVO(user));
    }

    @PostMapping("/add")
    @AuthCheck(mustRole = UserRoleEnum.ADMIN)
    public BaseResponse<Long> addUser(@RequestBody UserAddReq userAddRequest) {
        ThrowUtils.throwIf(userAddRequest == null, BaseResponseCode.PARAMS_ERROR);
        User user = new User();
        BeanUtils.copyProperties(userAddRequest, user);
        boolean result = userService.save(user);
        ThrowUtils.throwIf(!result, BaseResponseCode.OPERATION_ERROR);
        return ResultUtils.success(user.getId());
    }

    @PostMapping("/delete")
    @AuthCheck(mustRole = UserRoleEnum.ADMIN)
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(
                deleteRequest == null || deleteRequest.getId() <= 0,
                BaseResponseCode.PARAMS_ERROR
        );
        boolean b = userService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    @PostMapping("/update")
    @AuthCheck(mustRole = UserRoleEnum.ADMIN)
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateReq userUpdateRequest) {
        ThrowUtils.throwIf(
                userUpdateRequest == null || userUpdateRequest.getId() == null,
                BaseResponseCode.PARAMS_ERROR
        );
        User user = new User();
        BeanUtils.copyProperties(userUpdateRequest, user);
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, BaseResponseCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @GetMapping("/get")
    @AuthCheck(mustRole = UserRoleEnum.ADMIN)
    public BaseResponse<User> getUserById(long id) {
        ThrowUtils.throwIf(id <= 0, BaseResponseCode.PARAMS_ERROR);
        User user = userService.getById(id);
        ThrowUtils.throwIf(user == null, BaseResponseCode.NOT_FOUND_ERROR);
        return ResultUtils.success(user);
    }

    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserRoleEnum.ADMIN)
    public BaseResponse<Page<User>> listUserByPage(@RequestBody UserQueryReq userQueryRequest) {
        long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();
        Page<User> userPage = userService.page(new Page<>(current, size),
                userService.getQueryWrapper(userQueryRequest));
        return ResultUtils.success(userPage);
    }

    @PostMapping("/list/page/vo")
    public BaseResponse<Page<UserVO>> listUserVoByPage(@RequestBody UserQueryReq userQueryRequest) {
        ThrowUtils.throwIf(userQueryRequest == null, BaseResponseCode.PARAMS_ERROR);
        long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, BaseResponseCode.PARAMS_ERROR);
        Page<User> userPage = userService.page(new Page<>(current, size),
                userService.getQueryWrapper(userQueryRequest));
        Page<UserVO> userVoPage = new Page<>(current, size, userPage.getTotal());
        List<UserVO> userVO = userService.getUserVO(userPage.getRecords());
        userVoPage.setRecords(userVO);
        return ResultUtils.success(userVoPage);
    }
}
