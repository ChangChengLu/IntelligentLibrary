package com.cclu.intelligentlibrary.service.book.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.constant.CommonConstant;
import com.cclu.intelligentlibrary.mapper.BookMapper;
import com.cclu.intelligentlibrary.model.po.Book;
import com.cclu.intelligentlibrary.model.req.book.BookQueryReq;
import com.cclu.intelligentlibrary.service.book.BookService;
import com.cclu.intelligentlibrary.utils.SqlUtils;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author ChangChengLu
 * @description 针对表【book】的数据库操作Service实现
 * @createDate 2023-10-27 16:47:15
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
        implements BookService {

    @Override
    public QueryWrapper<Book> getQueryWrapper(BookQueryReq bookQueryReq) {
        ThrowUtils.throwIf(
                bookQueryReq == null,
                BaseResponseCode.PARAMS_ERROR
        );
        String bookName = bookQueryReq.getBookName();
        String bookAuthor = bookQueryReq.getBookAuthor();
        String bookDesc = bookQueryReq.getBookDesc();
        String bookPublisher = bookQueryReq.getBookPublisher();
        String bookIsbn = bookQueryReq.getBookIsbn();
        String bookTags = bookQueryReq.getBookTags();
        String sortField = bookQueryReq.getSortField();
        String sortOrder = bookQueryReq.getSortOrder();
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(bookName), "book_name", bookName);
        queryWrapper.like(StringUtils.isNotBlank(bookAuthor), "book_author", bookAuthor);
        queryWrapper.like(StringUtils.isNotBlank(bookDesc), "book_desc", bookDesc);
        queryWrapper.like(StringUtils.isNotBlank(bookPublisher), "book_publisher", bookPublisher);
        queryWrapper.eq(StringUtils.isNotBlank(bookIsbn), "book_isbn", bookIsbn);
        queryWrapper.like(StringUtils.isNotBlank(bookTags), "book_tags", bookTags);
        queryWrapper.orderBy(
                SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




