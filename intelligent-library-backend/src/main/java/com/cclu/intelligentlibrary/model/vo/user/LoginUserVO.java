package com.cclu.intelligentlibrary.model.vo.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/9/14 0:09
 */
@Data
public class LoginUserVO implements Serializable {

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
     * 用户邮箱地址
     */
    private String userEmail;

    /**
     * 用户地址(收货地址)
     */
    private String userAddress;

    private static final long serialVersionUID = 1L;

}
