package com.cclu.intellignetlibrary.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intellignetlibrary.common.response.BaseResponseCode;
import com.cclu.intellignetlibrary.constant.CommonConstant;
import com.cclu.intellignetlibrary.mapper.BookMapper;
import com.cclu.intellignetlibrary.model.entity.Book;
import com.cclu.intellignetlibrary.model.enums.BookStatusEnum;
import com.cclu.intellignetlibrary.model.req.book.BookQueryRequest;
import com.cclu.intellignetlibrary.model.vo.book.BookVO;
import com.cclu.intellignetlibrary.service.BookService;
import com.cclu.intellignetlibrary.service.UserService;
import com.cclu.intellignetlibrary.utils.SqlUtils;
import com.cclu.intellignetlibrary.utils.ThrowUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChangCheng Lu
 * @date 2023/9/9 13:51
 */
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
        implements BookService {

    @Resource
    private UserService userService;

    @Override
    public QueryWrapper<Book> getQueryWrapper(BookQueryRequest bookQueryRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(bookQueryRequest == null, BaseResponseCode.PARAMS_ERROR, "请求参数为空");
        Long id = bookQueryRequest.getId();
        String bookIsbn = bookQueryRequest.getBookIsbn();
        String bookName = bookQueryRequest.getBookName();
        String bookAuthor = bookQueryRequest.getBookAuthor();
        String bookPublisher = bookQueryRequest.getBookPublisher();
        String bookTags = bookQueryRequest.getBookTags();
        String bookDescription = bookQueryRequest.getBookDescription();
        Integer bookStatus = bookQueryRequest.getBookStatus();
        String sortField = bookQueryRequest.getSortOrder();
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(bookIsbn), "book_isbn", bookIsbn);
        queryWrapper.eq(StringUtils.isNotBlank(bookName), "book_name", bookName);
        queryWrapper.eq(StringUtils.isNotBlank(bookAuthor), "book_author", bookAuthor);
        queryWrapper.eq(StringUtils.isNotBlank(bookPublisher), "book_publisher", bookPublisher);
        queryWrapper.like(StringUtils.isNotBlank(bookTags), "book_tags", bookTags);
        queryWrapper.like(StringUtils.isNotBlank(bookDescription), "book_description", bookDescription);
        queryWrapper.orderBy(
                SqlUtils.validSortField(sortField),
                sortField.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        boolean isAdmin = userService.isAdmin(request);
        if (isAdmin) {
            queryWrapper.eq(id != null, "id", id);
            queryWrapper.eq(bookStatus != null, "book_status", bookStatus);
        } else {
            queryWrapper.eq("book_status", BookStatusEnum.TAKEN_ON.getCode());
        }
        return queryWrapper;
    }

    @Override
    public BookVO getBookVO(Book book) {
        if (book == null) {
            return null;
        }
        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(book, bookVO);
        return bookVO;
    }

    @Override
    public List<BookVO> getBookVO(List<Book> bookList) {
        if (CollectionUtils.isEmpty(bookList)) {
            return new ArrayList<>();
        }
        return bookList.stream().map(this::getBookVO).collect(Collectors.toList());
    }

}
