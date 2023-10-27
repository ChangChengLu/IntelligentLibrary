package com.cclu.intelligentlibrary.model.req.user;

import com.cclu.intelligentlibrary.common.request.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/10/10 16:14
 * @description
 * @copyright ChangChengLu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {

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
     * 用户描述
     */
    private String userDesc;

    /**
     * 0:admin(管理员)/1:normal_user(普通用户)/2:ban_user(禁用用户)
     */
    private Integer userRole;
    

    private static final long serialVersionUID = 1L;

}
