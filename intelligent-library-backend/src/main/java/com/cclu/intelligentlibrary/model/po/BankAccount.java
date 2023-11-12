package com.cclu.intelligentlibrary.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @author ChangChengLu
 * @TableName bank_account
 */
@TableName(value ="bank_account")
@Data
public class BankAccount implements Serializable {
    /**
     * 银行账户编号
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户编号
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 账户余额
     */
    @TableField(value = "user_balance")
    private BigDecimal userBalance;

    /**
     * 用户消费总额
     */
    @TableField(value = "user_total_cost")
    private BigDecimal userTotalCost;

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