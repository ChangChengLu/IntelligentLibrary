package com.cclu.intellignetlibrary.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ChangCheng Lu
 * @date 2023/9/14 0:09
 */
@Data
public class LoginUserVO {

    /**
     * id
     */
    @TableId
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
     * 用户电话
     */
    private String userPhone;

    /**
     * 用户电子邮箱
     */
    private String userEmail;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 用户角色：0:admin/1:vip1/2:vip2/3:vip3
     */
    private Integer userRole;

    /**
     * 创建时间
     */
    private BigDecimal balance;

}
