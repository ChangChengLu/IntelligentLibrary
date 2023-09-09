package com.cclu.intellignetlibrary.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ChangCheng Lu
 * @date 2023/9/9 13:36
 */
@TableName(value ="book_order")
@Data
public class BookOrder implements Serializable {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 购买用户
     */
    private Long userId;

    /**
     * 购买书籍
     */
    private Long bookId;

    /**
     * 购买数量
     */
    private Integer buyNumber;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 总价格（原价）
     */
    private BigDecimal totalPrice;

    /**
     * 会员价格（优惠价格）
     */
    private BigDecimal vipPrice;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
