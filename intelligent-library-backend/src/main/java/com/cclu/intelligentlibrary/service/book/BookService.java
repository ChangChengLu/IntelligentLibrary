package com.cclu.intelligentlibrary.service.book;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cclu.intelligentlibrary.model.po.Book;
import com.cclu.intelligentlibrary.model.req.book.BookQueryReq;

/**
* @author ChangChengLu
* @description 针对表【book】的数据库操作Service
* @createDate 2023-10-27 16:47:15
*/
public interface BookService extends IService<Book> {

    /**
     * 获取查询条件
     *
     * @param bookQueryReq 用户查询请求
     * @return 查询用户
     */
    QueryWrapper<Book> getQueryWrapper(BookQueryReq bookQueryReq);

}
