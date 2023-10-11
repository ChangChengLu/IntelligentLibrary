package com.cclu.intellignetlibrary.model.vo.book;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ChangCheng Lu
 * @date 2023/10/10 16:57
 * @description
 * @copyright 卢常诚 -- ChangChengLu
 */
@Data
public class BookVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 书籍图片
     */
    private String bookAvatar;

    /**
     * 书籍价格
     */
    private BigDecimal bookPrice;

    /**
     * 书籍描述
     */
    private String bookDescription;

    private static final long serialVersionUID = 1L;

}
