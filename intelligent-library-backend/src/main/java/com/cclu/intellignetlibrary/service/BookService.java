package com.cclu.intellignetlibrary.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cclu.intellignetlibrary.model.entity.Book;
import com.cclu.intellignetlibrary.model.req.book.BookQueryRequest;
import com.cclu.intellignetlibrary.model.vo.book.BookVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ChangCheng Lu
 * @date 2023/9/9 13:43
 */
public interface BookService extends IService<Book> {

    /**
     * 获取查询条件
     *
     * @param bookQueryRequest 书籍查询请求
     * @param request 用户请求
     * @return 查询条件
     */
    QueryWrapper<Book> getQueryWrapper(BookQueryRequest bookQueryRequest, HttpServletRequest request);


    /**
     * 获取脱敏的书籍信息
     *
     * @param book 未脱敏书籍信息
     * @return 托名书籍细信息
     */
    BookVO getBookVO(Book book);

    /**
     * 获取脱敏的书籍信息
     *
     * @param bookList 未脱敏书籍信息列表
     * @return 托名书籍细信息列表
     */
    List<BookVO> getBookVO(List<Book> bookList);
}
