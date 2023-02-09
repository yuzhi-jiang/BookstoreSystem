package com.example.BookstoreSystem.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.BookstoreSystem.client.entity.Book;
import com.example.BookstoreSystem.client.entity.Bookhot;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 
 *  BookHotMapper 接口
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface BookhotMapper extends BaseMapper<Bookhot> {

    @Update("update bookhot set hotnum=#{hotNum},updateBy=#{createby},updatetime=current_timestamp where bookid=#{bookid}")
    Boolean updateBookHot(Bookhot bookhot);

    @Select("select book.id,bookName,label,author,hotnum,introduction from book join bookhot as bh on book.id=bh.bookid\n" +
            "join booktype as bt on book.typeid = bt.id order by hotnum asc limit 10")
    List<Book> selectTopTenBookHot();


    @Select("select book.id,bookName,label,author,hotnum,introduction from book join bookhot as bh on book.id=bh.bookid\n"+
            "join booktype as bt on book.typeid = bt.id limit #{start},#{pageSize}")
    List<Book> selectBookHotByPage(int start, int pageSize);

    @Select("select book.id,bookName,label,author,hotnum,introduction from book join bookhot as bh on book.id=bh.bookid\n" +
            "join booktype as bt on book.typeid = bt.id")
    List<Book> selectAllBookHot();
}
