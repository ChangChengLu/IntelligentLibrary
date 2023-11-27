package com.cclu.intelligentlibrary.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author ChangChengLu
 * @TableName vip
 */
@TableName(value ="vip")
@Data
public class Vip implements Serializable {
    /**
     * 会员编号
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户编号
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 0:ordinary(普通会员)/1:gold(金牌会员)/2:platinum(白金会员)
     */
    @TableField(value = "vip_type")
    private Integer vipType;

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