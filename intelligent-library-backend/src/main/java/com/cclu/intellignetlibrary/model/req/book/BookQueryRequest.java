package com.cclu.intellignetlibrary.model.req.book;

import com.baomidou.mybatisplus.annotation.TableId;
import com.cclu.intellignetlibrary.common.request.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/10/10 16:44
 * @description
 * @copyright 卢常诚 -- ChangChengLu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BookQueryRequest extends PageRequest implements Serializable {

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
     * 书籍名称
     */
    private String bookName;

    /**
     * 书籍作者
     */
    private String bookAuthor;

    /**
     * 书籍出版社
     */
    private String bookPublisher;

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
