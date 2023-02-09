package com.example.BookstoreSystem.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.BookstoreSystem.client.entity.Booktype;
import com.example.BookstoreSystem.client.mapper.BooktypeMapper;
import com.example.BookstoreSystem.client.service.IBooktypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class BooktypeService extends ServiceImpl<BooktypeMapper, Booktype> implements IBooktypeService {


    @Resource
    BooktypeMapper mapper;

    @Override
    public List<Booktype> getAllBookType() {
        return mapper.selectAllBookType();
    }

    @Override
    public boolean addBookType(Booktype booktype) {
        return mapper.insertBookType(booktype);
    }

    @Override
    public List<Booktype> getAllBookTypeList(int page, int limit) {
        int start = (page-1)*limit;
        return mapper.selectAllBookTypeList(start,limit);
    }

    @Override
    public Long getBookTypeCount() {
        return mapper.selecBookTypeCount();
    }

    @Override
    public boolean updateBookTypeById(Booktype booktype) {
        return mapper.updateBookTypeById(booktype);
    }

    @Override
    public List<Booktype> getBookTypeByType(Long parentId) {
        return mapper.selectBookTypeByParentId(parentId);
    }
}
