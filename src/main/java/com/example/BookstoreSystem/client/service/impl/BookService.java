package com.example.BookstoreSystem.client.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.BookstoreSystem.client.bean.BBook;
import com.example.BookstoreSystem.client.entity.Book;
import com.example.BookstoreSystem.client.entity.Booktype;
import com.example.BookstoreSystem.client.mapper.BookMapper;
import com.example.BookstoreSystem.client.service.IBookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 *
 *  服务实现类
 *
 *
 * @author yefeng
 * @since 2022-04-06
 */
@Service
public class BookService extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Resource

    BookMapper mapper;





    @Override
    public List<Booktype> getAllBooktype() {
        List<Booktype> allBookType = mapper.findALLBookType();
        return allBookType;
    }

    @Override
    public List<Book> getBookByTypeId(Integer type) {
        List<Book> books = mapper.findBooksByTypeId((long) type);
        return books;
    }

    @Override
    public List<Book> getBookByName(String name) {
        return null;
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> getBookByPress(String press) {
        return null;
    }


    @Override
    public List<Book>getBooksById(Long id){
        return mapper.findBooksByTypeId(id);
    }


    //提交事务
    @Transactional
    protected HashMap<String,Object> getAllBooks(){
        List<Booktype> allBookType = getAllBooktype();
        HashMap<String, Object> map = new HashMap<>();
        allBookType.forEach(bookType -> {
            List<Book> books =getBookByTypeId(bookType.getId());
            map.put(bookType.getKey(),books);
        });
        return map;
    }

    @Override
    public JSONObject getAllBook() {
        HashMap<String, Object> allBooks = getAllBooks();

        return new JSONObject(allBooks);
    }

    @Override
    public boolean addBook(Book book) {
      return   mapper.addBook(book);
    }

    @Override
    public boolean updateBook(Book book) {
       return mapper.updateBook(book);
    }

    @Override
    public Long getBookCount() {
        return mapper.selectBookCount();
    }

    @Override
    public HashMap<String, Object> getAllPreviewBook() {
        return getAllPreviewBooks();
    }

    @Override
    public List<Book> getBookList(int page, int limit) {
        int start=limit*(page-1);
        return mapper.selectBookList(start,limit);
    }

    @Transactional
    public HashMap<String, Object> getAllPreviewBooks() {
        HashMap<String, Object> map = new HashMap<>();
        List<Booktype> booktypeList = getAllBooktype();
        booktypeList.forEach(booktype -> {
            List<BBook> list = mapper.selectPreviewBookByTypeid(Long.valueOf(booktype.getId()));
            map.put(booktype.getKey(),list);
        });
        return map;
    }
}
