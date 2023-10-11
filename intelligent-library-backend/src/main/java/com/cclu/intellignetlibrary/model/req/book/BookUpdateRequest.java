package com.cclu.intellignetlibrary.model.req.book;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ChangCheng Lu
 * @date 2023/10/10 16:36
 * @description
 * @copyright 卢常诚 -- ChangChengLu
 */
@Data
public class BookUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 书籍ISBN编号
     */
    private String bookIsbn;

    /**
     * 书籍名称
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
     * 书籍描述
     */
    private String bookDescription;

    /**
     * 书籍状态：0未上架/1已上架/2已下架
     */
    private Integer bookStatus;

}
