package com.cclu.intelligentlibrary.service.book.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.intelligentlibrary.mapper.BookMapper;
import com.cclu.intelligentlibrary.model.po.Book;
import com.cclu.intelligentlibrary.service.book.BookService;
import org.springframework.stereotype.Service;

/**
* @author ChangChengLu
* @description 针对表【book】的数据库操作Service实现
* @createDate 2023-10-27 16:47:15
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService {

}




