package com.cclu.intelligentlibrary.model.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @author ChangChengLu
 * @TableName borrow
 */
@TableName(value ="borrow")
@Data
public class Borrow implements Serializable {
    /**
     * 借阅编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 书籍ID
     */
    @TableField(value = "book_id")
    private Long bookId;

    /**
     * 借阅开始时间
     */
    @TableField(value = "borrow_start_time")
    private Date borrowStartTime;

    /**
     * 借阅天数
     */
    @TableField(value = "borrow_day")
    private Date borrowDay;

    /**
     * 0:borrowing(借阅中)/1:returned(已归还)
     */
    @TableField(value = "borrow_state")
    private Integer borrowState;

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