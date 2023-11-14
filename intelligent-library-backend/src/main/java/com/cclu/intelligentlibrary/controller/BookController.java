package com.cclu.intelligentlibrary.controller;

import com.cclu.intelligentlibrary.common.response.BaseResponse;
import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.model.po.Book;
import com.cclu.intelligentlibrary.model.req.book.BookAddReq;
import com.cclu.intelligentlibrary.model.req.book.BookUpdateReq;
import com.cclu.intelligentlibrary.service.book.BookService;
import com.cclu.intelligentlibrary.utils.ResultUtils;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ChangCheng Lu
 * @date 2023/11/2 20:12
 * @description
 * @copyright ChangChengLu
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping("/add")
    public BaseResponse<Long> addBook(@RequestBody BookAddReq bookAddReq) {
        ThrowUtils.throwIf(
                bookAddReq == null,
                BaseResponseCode.PARAMS_ERROR
        );
        Book book = new Book();
        BeanUtils.copyProperties(bookAddReq, book);
        boolean save = bookService.save(book);
        ThrowUtils.throwIf(!save, BaseResponseCode.SYSTEM_ERROR);
        return ResultUtils.success(book.getId());
    }

    @GetMapping("/del")
    public BaseResponse<Boolean> delBookById(Long id) {
        ThrowUtils.throwIf(
                id == null || id <= 0,
                BaseResponseCode.PARAMS_ERROR
        );
        boolean save = bookService.removeById(id);
        ThrowUtils.throwIf(
                !save,
                BaseResponseCode.PARAMS_ERROR
        );
        return ResultUtils.success(true);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateBook(@RequestBody BookUpdateReq bookUpdateReq) {
        ThrowUtils.throwIf(
                bookUpdateReq == null,
                BaseResponseCode.PARAMS_ERROR
        );
        Book book = new Book();
        BeanUtils.copyProperties(bookUpdateReq, book);
        boolean update = bookService.saveOrUpdate(book);
        ThrowUtils.throwIf(
                !update,
                BaseResponseCode.PARAMS_ERROR
        );
        return ResultUtils.success(true);
    }

}
