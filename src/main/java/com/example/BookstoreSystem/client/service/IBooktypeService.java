package com.example.BookstoreSystem.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.BookstoreSystem.client.entity.Booktype;

import java.util.List;

/**
 * 
 *  服务类
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface IBooktypeService extends IService<Booktype> {

    List<Booktype> getAllBookType();

    boolean addBookType(Booktype booktype);

    List<Booktype> getBookTypeByType(Long parentId);

    List<Booktype> getAllBookTypeList(int page, int limit);

    Long getBookTypeCount();

    boolean updateBookTypeById(Booktype booktype);
}
