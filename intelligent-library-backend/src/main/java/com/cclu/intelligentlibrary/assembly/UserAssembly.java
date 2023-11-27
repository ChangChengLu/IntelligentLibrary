package com.cclu.intelligentlibrary.assembly;

import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.model.po.User;
import com.cclu.intelligentlibrary.model.vo.user.UserVO;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChangCheng Lu
 * @date 2023/11/25 19:12
 * @description
 * @copyright ChangChengLu
 */
public class UserAssembly {

    public static UserVO user2UserVO(User user) {
        ThrowUtils.throwIf(user == null, BaseResponseCode.PARAMS_ERROR);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    public static List<UserVO> user2UserVO(List<User> userList) {
        ThrowUtils.throwIf(
                userList == null || userList.isEmpty(),
                BaseResponseCode.PARAMS_ERROR
        );
        return userList.stream().map(UserAssembly::user2UserVO).collect(Collectors.toList());
    }

}
