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
@TableName(value ="book")
@Data
public class Book implements Serializable {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 书籍ISBN编号
     */
    private String bookIsbn;

    /**
     * 书籍作者
     */
    private String bookName;

    /**
     * 书籍图片
     */
    private String bookAvatar;

    /**
     * 书籍作者
     */
    private String bookAuthor;

    /**
     * 书籍出版社
     */
    private String bookPublisher;

    /**
     * 书籍价格
     */
    private BigDecimal bookPrice;

    /**
     * 书籍借阅价格(按天结算)
     */
    private BigDecimal borrowPricePerDay;

    /**
     * 书籍库存
     */
    private Integer bookStock;

    /**
     * 书籍标签
     */
    private String bookTags;

    /**
     * 购买总数量
     */
    private Integer buyNumber;

    /**
     * 借阅总数量
     */
    private Integer borrowNumber;

    /**
     * 书籍描述
     */
    private String bookDescription;

    /**
     * 书籍状态：0未上架/1已上架/2已下架
     */
    private Integer bookStatus;

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
