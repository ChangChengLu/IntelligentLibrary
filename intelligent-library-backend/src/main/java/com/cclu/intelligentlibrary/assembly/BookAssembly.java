package com.cclu.intelligentlibrary.assembly;

import com.cclu.intelligentlibrary.model.po.Book;
import com.cclu.intelligentlibrary.model.vo.book.BookInfo;
import com.cclu.intelligentlibrary.model.vo.book.BookVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChangCheng Lu
 * @date 2023/11/21 17:23
 * @description
 * @copyright ChangChengLu
 */
public class BookAssembly {

    public static BookVO book2BookVO(Book book) {
        BookVO bookVO = new BookVO();
        BeanUtils.copyProperties(book, bookVO);
        return bookVO;
    }

    public static List<BookVO> book2BookVO(List<Book> bookList) {
        return bookList.stream().map(BookAssembly::book2BookVO).collect(Collectors.toList());
    }

    public static BookInfo book2BookInfo(Book book) {
        BookInfo bookInfo = new BookInfo();
        BeanUtils.copyProperties(book, bookInfo);
        return bookInfo;
    }

    public static List<BookInfo> book2BookInfo(List<Book> bookList) {
        return bookList.stream().map(BookAssembly::book2BookInfo).collect(Collectors.toList());
    }

}
