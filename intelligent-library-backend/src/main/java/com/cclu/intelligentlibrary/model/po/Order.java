package com.cclu.intelligentlibrary.model.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @author ChangChengLu
 * @TableName order
 */
@TableName(value ="order")
@Data
public class Order implements Serializable {
    /**
     * 订单编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 下单金额
     */
    @TableField(value = "order_amount")
    private BigDecimal orderAmount;

    /**
     * 实付款金额
     */
    @TableField(value = "order_pay_amount")
    private BigDecimal orderPayAmount;

    /**
     * 下单时间
     */
    @TableField(value = "order_time")
    private Date orderTime;

    /**
     * 0:paying(未付款)/1:payed(已付款)
     */
    @TableField(value = "order_state")
    private Integer orderState;

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