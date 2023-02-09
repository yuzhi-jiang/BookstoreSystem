package com.example.BookstoreSystem.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.BookstoreSystem.client.entity.Book;
import com.example.BookstoreSystem.client.entity.Bookhot;

import java.util.List;

/**
 * 
 *  服务类
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface IBookhotService extends IService<Bookhot> {

    List<Book> getAllBookHot();

    List<Book> getBookHotByPage(int currPage, int pageSize);

    List<Book> getTopTenBookHot();

    Boolean updateBookHot(Bookhot bookhot);
}
