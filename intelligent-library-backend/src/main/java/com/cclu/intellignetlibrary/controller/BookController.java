package com.cclu.intellignetlibrary.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cclu.intellignetlibrary.annotation.AuthCheck;
import com.cclu.intellignetlibrary.common.request.DeleteRequest;
import com.cclu.intellignetlibrary.common.response.BaseResponse;
import com.cclu.intellignetlibrary.common.response.BaseResponseCode;
import com.cclu.intellignetlibrary.model.entity.Book;
import com.cclu.intellignetlibrary.model.enums.UserRoleEnum;
import com.cclu.intellignetlibrary.model.req.book.BookAddRequest;
import com.cclu.intellignetlibrary.model.req.book.BookQueryRequest;
import com.cclu.intellignetlibrary.model.req.book.BookUpdateRequest;
import com.cclu.intellignetlibrary.model.vo.book.BookDetailVO;
import com.cclu.intellignetlibrary.model.vo.book.BookVO;
import com.cclu.intellignetlibrary.service.BookService;
import com.cclu.intellignetlibrary.utils.ResultUtils;
import com.cclu.intellignetlibrary.utils.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ChangCheng Lu
 * @date 2023/10/10 16:27
 * @description
 * @copyright 卢常诚 -- ChangChengLu
 */
@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping("/add")
    @AuthCheck(mustRole = UserRoleEnum.ADMIN)
    public BaseResponse<Long> addBook(@RequestBody BookAddRequest bookAddRequest) {
        ThrowUtils.throwIf(bookAddRequest == null, BaseResponseCode.PARAMS_ERROR);
        Book book = new Book();
        BeanUtils.copyProperties(bookAddRequest, book);
        boolean result = bookService.save(book);
        ThrowUtils.throwIf(!result, BaseResponseCode.OPERATION_ERROR);
        return ResultUtils.success(book.getId());
    }

    @PostMapping("/delete")
    @AuthCheck(mustRole = UserRoleEnum.ADMIN)
    public BaseResponse<Boolean> deleteBook(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(
                deleteRequest == null || deleteRequest.getId() <= 0,
                BaseResponseCode.PARAMS_ERROR
        );
        boolean b = bookService.removeById(deleteRequest.getId());
        return ResultUtils.success(b);
    }

    @PostMapping("/update")
    @AuthCheck(mustRole = UserRoleEnum.ADMIN)
    public BaseResponse<Boolean> updateBook(@RequestBody BookUpdateRequest bookUpdateRequest) {
        ThrowUtils.throwIf(
                bookUpdateRequest == null || bookUpdateRequest.getId() == null,
                BaseResponseCode.PARAMS_ERROR
        );
        Book book = new Book();
        BeanUtils.copyProperties(bookUpdateRequest, book);
        boolean result = bookService.updateById(book);
        ThrowUtils.throwIf(!result, BaseResponseCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @GetMapping("/get")
    @AuthCheck(mustRole = UserRoleEnum.ADMIN)
    public BaseResponse<Book> getBookById(long id) {
        ThrowUtils.throwIf(id <= 0, BaseResponseCode.PARAMS_ERROR);
        Book book = bookService.getById(id);
        ThrowUtils.throwIf(book == null, BaseResponseCode.NOT_FOUND_ERROR);
        return ResultUtils.success(book);
    }

    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserRoleEnum.ADMIN)
    public BaseResponse<Page<Book>> listBookByPage(@RequestBody BookQueryRequest bookQueryRequest, 
                                                   HttpServletRequest request) {
        long current = bookQueryRequest.getCurrent();
        long size = bookQueryRequest.getPageSize();
        Page<Book> bookPage = bookService.page(new Page<>(current, size),
                bookService.getQueryWrapper(bookQueryRequest, request));
        return ResultUtils.success(bookPage);
    }

    @PostMapping("/list/page/vo")
    public BaseResponse<Page<BookVO>> listBookVoByPage(@RequestBody BookQueryRequest bookQueryRequest,
                                                       HttpServletRequest request) {
        ThrowUtils.throwIf(bookQueryRequest == null, BaseResponseCode.PARAMS_ERROR);
        long current = bookQueryRequest.getCurrent();
        long size = bookQueryRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, BaseResponseCode.PARAMS_ERROR);
        Page<Book> bookPage = bookService.page(new Page<>(current, size),
                bookService.getQueryWrapper(bookQueryRequest, request));
        Page<BookVO> bookVoPage = new Page<>(current, size, bookPage.getTotal());
        List<BookVO> bookVO = bookService.getBookVO(bookPage.getRecords());
        bookVoPage.setRecords(bookVO);
        return ResultUtils.success(bookVoPage);
    }

    @GetMapping("/get/vo/detail")
    public BaseResponse<BookDetailVO> getBookDetailVO(long id) {
        ThrowUtils.throwIf(id <= 0, BaseResponseCode.PARAMS_ERROR);
        Book book = bookService.getById(id);
        ThrowUtils.throwIf(book == null, BaseResponseCode.NOT_FOUND_ERROR);
        BookDetailVO bookDetailVO = new BookDetailVO();
        BeanUtils.copyProperties(book, bookDetailVO);
        return ResultUtils.success(bookDetailVO);
    }
    
}
