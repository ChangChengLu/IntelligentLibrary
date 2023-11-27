package com.cclu.intelligentlibrary.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cclu.intelligentlibrary.assembly.BookAssembly;
import com.cclu.intelligentlibrary.common.request.IdReq;
import com.cclu.intelligentlibrary.common.response.BaseResponse;
import com.cclu.intelligentlibrary.common.response.BaseResponseCode;
import com.cclu.intelligentlibrary.model.po.Book;
import com.cclu.intelligentlibrary.model.req.book.BookAddReq;
import com.cclu.intelligentlibrary.model.req.book.BookQueryReq;
import com.cclu.intelligentlibrary.model.req.book.BookUpdateReq;
import com.cclu.intelligentlibrary.model.vo.book.BookInfo;
import com.cclu.intelligentlibrary.model.vo.book.BookVO;
import com.cclu.intelligentlibrary.service.book.BookService;
import com.cclu.intelligentlibrary.utils.ResultUtils;
import com.cclu.intelligentlibrary.utils.ThrowUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @PostMapping("/get/info")
    public BaseResponse<BookInfo> getBookInfoById(@RequestBody IdReq req) {
        ThrowUtils.throwIf(
                req == null || req.getId() < 0,
                BaseResponseCode.PARAMS_ERROR
        );
        Book book = bookService.getById(req.getId());
        ThrowUtils.throwIf(book == null, BaseResponseCode.SYSTEM_ERROR, "书籍不存在");
        BookInfo bookInfo = BookAssembly.book2BookInfo(book);
        return ResultUtils.success(bookInfo);
    }

    @PostMapping("/get/vo")
    public BaseResponse<BookVO> getBookVoById(@RequestBody IdReq req) {
        ThrowUtils.throwIf(
                req == null || req.getId() < 0,
                BaseResponseCode.PARAMS_ERROR
        );
        Book book = bookService.getById(req.getId());
        ThrowUtils.throwIf(book == null, BaseResponseCode.SYSTEM_ERROR, "书籍不存在");
        BookVO bookVO = BookAssembly.book2BookVO(book);
        return ResultUtils.success(bookVO);
    }


    @PostMapping("/list/page/vo")
    public BaseResponse<Page<BookVO>> listBookV0ByPage(@RequestBody BookQueryReq bookQueryReq) {
        long current = bookQueryReq.getCurrent();
        long size = bookQueryReq.getPageSize();
        Page<Book> bookPage = getPageBook(bookQueryReq);
        Page<BookVO> bookVoPage = new Page<>(current, size, bookPage.getTotal());
        List<BookVO> bookVOList = BookAssembly.book2BookVO(bookPage.getRecords());
        bookVoPage.setRecords(bookVOList);
        return ResultUtils.success(bookVoPage);
    }

    @PostMapping("/list/page/info")
    public BaseResponse<Page<BookInfo>> listBookInfoByPage(@RequestBody BookQueryReq bookQueryReq) {
        long current = bookQueryReq.getCurrent();
        long size = bookQueryReq.getPageSize();
        Page<Book> bookPage = getPageBook(bookQueryReq);
        Page<BookInfo> bookInfoPage = new Page<>(current, size, bookPage.getTotal());
        List<BookInfo> bookInfoList = BookAssembly.book2BookInfo(bookPage.getRecords());
        bookInfoPage.setRecords(bookInfoList);
        return ResultUtils.success(bookInfoPage);
    }

    private Page<Book> getPageBook(BookQueryReq bookQueryReq) {
        ThrowUtils.throwIf(bookQueryReq == null, BaseResponseCode.PARAMS_ERROR);
        long current = bookQueryReq.getCurrent();
        long size = bookQueryReq.getPageSize();
        ThrowUtils.throwIf(size > 20, BaseResponseCode.PARAMS_ERROR);
        return bookService.page(new Page<>(current, size), bookService.getQueryWrapper(bookQueryReq));
    }
}
