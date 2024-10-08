package com.cclu.intelligentlibrary.model.req.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/10/10 11:33
 * @description
 * @copyright ChangChengLu
 */
@Data
public class UserRegisterReq implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

}
