package com.example.BookstoreSystem.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.BookstoreSystem.client.entity.Book;
import com.example.BookstoreSystem.client.entity.Bookhot;
import com.example.BookstoreSystem.client.mapper.BookhotMapper;
import com.example.BookstoreSystem.client.service.IBookhotService;
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
public class BookhotService extends ServiceImpl<BookhotMapper, Bookhot> implements IBookhotService {

    @Resource  //按类型注入
    BookhotMapper mapper;

    @Override
    public List<Book> getAllBookHot() {
        return mapper.selectAllBookHot();
    }

    @Override
    public List<Book> getBookHotByPage(int currPage, int pageSize) {

        int start=(currPage-1)*pageSize;
        return mapper.selectBookHotByPage(start,pageSize);

    }

    @Override
    public List<Book> getTopTenBookHot() {
        return mapper.selectTopTenBookHot();
    }

    @Override
    public Boolean updateBookHot(Bookhot bookhot) {
        return mapper.updateBookHot(bookhot);
    }
}
