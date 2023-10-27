package com.cclu.intelligentlibrary.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户编号
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户账户
     */
    @TableField(value = "user_account")
    private String userAccount;

    /**
     * 用户密码
     */
    @TableField(value = "user_password")
    private String userPassword;

    /**
     * 用户昵称
     */
    @TableField(value = "user_nickname")
    private String userNickname;

    /**
     * 用户头像
     */
    @TableField(value = "user_avatar")
    private String userAvatar;

    /**
     * 用户描述
     */
    @TableField(value = "user_desc")
    private String userDesc;

    /**
     * 用户性别
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 0:admin(管理员)/1:normal_user(普通用户)/2:ban_user(禁用用户)
     */
    @TableField(value = "user_role")
    private Integer userRole;

    /**
     * 用户邮箱地址
     */
    @TableField(value = "user_email")
    private String userEmail;

    /**
     * 用户联系电话
     */
    @TableField(value = "user_phone")
    private String userPhone;

    /**
     * 用户地址(收货地址)
     */
    @TableField(value = "user_address")
    private String userAddress;

    /**
     * 数据创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 数据更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}