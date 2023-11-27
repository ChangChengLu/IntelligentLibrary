package com.cclu.intelligentlibrary.model.vo.book;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ChangCheng Lu
 * @date 2023/11/2 20:15
 * @description 书籍简略信息
 * @copyright ChangChengLu
 */
@Data
public class BookInfo implements Serializable {

    /**
     * 书籍编号
     */
    private Long id;

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 书籍封面
     */
    private String bookAvatar;

    /**
     * 书籍作者
     */
    private String bookAuthor;

    /**
     * 书籍描述
     */
    private String bookDesc;

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

}
