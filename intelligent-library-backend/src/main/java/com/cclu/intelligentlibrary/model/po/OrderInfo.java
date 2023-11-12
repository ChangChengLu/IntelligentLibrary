package com.cclu.intelligentlibrary.model.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @author ChangChengLu
 * @TableName order_info
 */
@TableName(value ="order_info")
@Data
public class OrderInfo implements Serializable {
    /**
     * 订单明细编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
订单编号
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 书籍ID
     */
    @TableField(value = "book_id")
    private Long bookId;

    /**
     * 下单金额
     */
    @TableField(value = "order_info_amount")
    private BigDecimal orderInfoAmount;

    /**
     * 实付款金额
     */
    @TableField(value = "order_info_pay_amount")
    private BigDecimal orderInfoPayAmount;

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