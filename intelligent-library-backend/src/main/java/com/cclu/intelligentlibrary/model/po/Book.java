package com.cclu.intelligentlibrary.model.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author ChangChengLu
 * @TableName book
 */
@TableName(value ="book")
@Data
public class Book implements Serializable {
    /**
     * 书籍编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 书籍名称
     */
    @TableField(value = "book_name")
    private String bookName;

    /**
     * 书籍作者
     */
    @TableField(value = "book_author")
    private String bookAuthor;

    /**
     * 书籍封面
     */
    @TableField(value = "book_avatar")
    private String bookAvatar;

    /**
     * 书籍描述
     */
    @TableField(value = "book_desc")
    private String bookDesc;

    /**
     * 书籍出版商
     */
    @TableField(value = "book_publisher")
    private String bookPublisher;

    /**
     * ISBN
     */
    @TableField(value = "book_isbn")
    private String bookIsbn;

    /**
     * 书籍标签
     */
    @TableField(value = "book_tags")
    private String bookTags;

    /**
     * 0:shelving(未上架)/1:auditing(审核中)/2:shelved(已上架)/3:removed(已下架)
     */
    @TableField(value = "book_state")
    private Integer bookState;

    /**
     * 书籍零售价
     */
    @TableField(value = "book_price")
    private BigDecimal bookPrice;

    /**
     * 书籍借阅价格(每天)
     */
    @TableField(value = "book_borrow_price")
    private BigDecimal bookBorrowPrice;

    /**
     * 书籍库存
     */
    @TableField(value = "book_stock")
    private Integer bookStock;

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