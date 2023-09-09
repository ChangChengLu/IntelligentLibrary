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
@TableName(value ="book_borrow")
@Data
public class BookBorrow implements Serializable {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 书籍ID
     */
    private Integer bookId;

    /**
     * 借阅书籍数量
     */
    private Integer borrowNumber;

    /**
     * 借阅总价格（原价）
     */
    private BigDecimal totalPrice;

    /**
     * 会员价格（优惠后）
     */
    private BigDecimal vipPrice;

    /**
     * 借阅天数
     */
    private Integer borrowDay;

    /**
     *
     */
    private Date returnData;

    /**
     * 借阅时间
     */
    private Date borrowData;

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
