package com.example.BookstoreSystem.client.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.BookstoreSystem.client.entity.Book;
import com.example.BookstoreSystem.client.entity.Booktype;

import java.util.HashMap;
import java.util.List;

/**
 * 服务类
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface IBookService extends IService<Book> {
    List<Booktype> getAllBooktype();

    List<Book> getBookByTypeId(Integer type);

    List<Book> getBookByName(String name);

    List<Book> getBookByAuthor(String author);

    List<Book> getBookByPress(String press);

    List<Book> getBooksById(Long id);

    JSONObject getAllBook();

    boolean addBook(Book book);

    boolean updateBook(Book book);

    Long getBookCount();

    HashMap<String, Object> getAllPreviewBook();

    List<Book> getBookList(int page, int limit);
}
