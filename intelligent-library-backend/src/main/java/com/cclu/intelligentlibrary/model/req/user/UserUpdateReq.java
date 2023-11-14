package com.cclu.intelligentlibrary.model.req.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/10/10 16:06
 * @description
 * @copyright ChangChengLu
 */
@Data
public class UserUpdateReq implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    private static final long serialVersionUID = 1L;

}
