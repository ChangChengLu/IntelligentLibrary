package com.cclu.intelligentlibrary.model.req.book;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ChangCheng Lu
 * @date 2023/11/12 20:08
 * @description
 * @copyright ChangChengLu
 */
@Data
public class BookAddReq implements Serializable {

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 书籍作者
     */
    private String bookAuthor;

    /**
     * 书籍封面
     */
    private String bookAvatar;

    /**
     * 书籍描述
     */
    private String bookDesc;

    /**
     * 书籍出版商
     */
    private String bookPublisher;

    /**
     * ISBN
     */
    private String bookIsbn;

    /**
     * 书籍标签
     */
    private String bookTags;

    /**
     * 书籍零售价
     */
    private BigDecimal bookPrice;

    /**
     * 书籍借阅价格(每天)
     */
    private BigDecimal bookBorrowPrice;

    /**
     * 书籍库存
     */
    private Integer bookStock;

    private static final long serialVersionUID = 1L;

}
