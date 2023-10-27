package com.cclu.intelligentlibrary.model.vo.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ChangCheng Lu
 * @date 2023/10/10 16:20
 * @description
 * @copyright ChangChengLu
 */
@Data
public class UserVO implements Serializable {

    /**
     * 用户编号
     */
    private Long id;

    /**
     * 用户账户
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userNickname;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户描述
     */
    private String userDesc;

    /**
     * 用户性别
     */
    private Integer gender;

    /**
     * 0:admin(管理员)/1:normal_user(普通用户)/2:ban_user(禁用用户)
     */
    private Integer userRole;

    /**
     * 用户邮箱地址
     */
    private String userEmail;

    /**
     * 用户联系电话
     */
    private String userPhone;

    /**
     * 用户地址(收货地址)
     */
    private String userAddress;

    private static final long serialVersionUID = 1L;

}
