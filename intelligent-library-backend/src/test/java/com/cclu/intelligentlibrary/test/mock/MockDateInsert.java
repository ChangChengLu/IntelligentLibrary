package com.cclu.intelligentlibrary.test.mock;

import cn.hutool.core.lang.Assert;
import com.cclu.intelligentlibrary.model.po.Book;
import com.cclu.intelligentlibrary.service.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author ChangCheng Lu
 * @date 2023/11/25 15:11
 * @description
 * @copyright ChangChengLu
 */
@SpringBootTest
@Slf4j
public class MockDateInsert {

    @Resource
    private BookService bookService;

    @Test
    public void insertBookMockData() {
        List<Book> mockData = new ArrayList<>();

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Book book = new Book();
            book.setBookName("book_"+i);
            book.setBookAuthor("author_"+i);
            book.setBookAvatar("avatar_"+i);
            book.setBookDesc("bookDesc_"+i);
            book.setBookPublisher("bookPublisher_"+i);
            book.setBookIsbn("isbn");
            book.setBookTags("[]");
            book.setBookPrice(new BigDecimal("10"));
            book.setBookBorrowPrice(new BigDecimal("5"));
            book.setBookStock(10);
            mockData.add(book);
        });

        IntStream.rangeClosed(101, 200).forEach(i -> {
            Book book = new Book();
            book.setBookName("book_"+i);
            book.setBookAuthor("author_"+i);
            book.setBookAvatar("avatar_"+i);
            book.setBookDesc("bookDesc_"+i);
            book.setBookPublisher("bookPublisher_"+i);
            book.setBookIsbn("isbn");
            book.setBookTags("[]");
            book.setBookPrice(new BigDecimal("10"));
            book.setBookBorrowPrice(new BigDecimal("5"));
            book.setBookStock(10);
            mockData.add(book);
        });

        boolean b = bookService.saveBatch(mockData);
        Assert.isTrue(b);
    }

}
