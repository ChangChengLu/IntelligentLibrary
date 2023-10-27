package com.cclu.intelligentlibrary.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName order_relation
 */
@TableName(value ="order_relation")
@Data
public class OrderRelation implements Serializable {
    /**
     * 借阅编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单编号
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 订单明细号
     */
    @TableField(value = "order_info_id")
    private Long orderInfoId;

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