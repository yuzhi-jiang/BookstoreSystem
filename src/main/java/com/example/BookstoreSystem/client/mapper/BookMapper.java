package com.example.BookstoreSystem.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.BookstoreSystem.client.bean.BBook;
import com.example.BookstoreSystem.client.entity.Book;
import com.example.BookstoreSystem.client.entity.Booktype;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 
 *  Mapper 接口
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    /**
     * @title findALLBookType
     * @description 获取书的所有类型
     * @author yefeng
     * @Param
     * @updateTime 2022/3/29 21:14
     */
    @Select("select * from bookType")
    public List<Booktype> findALLBookType();



    /**
     * @title findBookTypeByTypeid
     * @description 根据书类型id查找书类型 BookType
     * @author yefeng
     * @Param [tid]
     * @updateTime 2022/3/29 21:13
     * @return: com.example.BookstoreSystem.client.entity.BookType
     */
    @Select("select * from booktype where id=#{tid}")
    public Booktype findBookTypeByTypeid(@Param("tid") Long tid);






    /**
     * @title findBooksByTypeId
     * @description 根据书的id获取所有书
     * @author yefeng
     * @Param [tid]
     * @updateTime 2022/3/29 21:12
     * @return: java.util.List<com.example.BookstoreSystem.client.entity.Book>
     */
    @Select("select * from book where typeid=#{tid}")
    public List<Book> findBooksByTypeId(@Param("tid")Long tid);




    /**
     * @title findBookByTypeIdAndLimit
     * @description 根据书类型id和限制数，查找书
     * @author yefeng
     * @Param [tid, limit]
     * @updateTime 2022/3/29 21:10
     * @return: java.util.List<com.example.BookstoreSystem.client.entity.Book>
     */
    @Select("select * from book where typeid=#{tid} limit #{limit}")
    public List<Book> findBooksByTypeIdAndLimit(@Param("tid")Long tid,@Param("limit") Integer limit);

    @Insert("insert into book(typeid,isbn,author,price,bookid,binding,foliox,folioy,foliosize,kucun,s_id,catalogue,introduction,longPic,createBy,description,bookName)" +
            " values(#typeid,#isbn,#author,#price,#bookid,#binding,#foliox,#folioy,#foliosize,#kucun,#s_id,#catalogue,#introduction,#longPic,#createBy,#description,#bookName)")
    boolean addBook(Book book);

    @Update("update book set(typeid,isbn,publishedtime,author,price,bookid,binding,foliox,folioy,foliosize,kucun,s_id,catalogue,introduction,longPic,createBy,description,bookName) values(#typeid,#isbn,#publishedtime,#author,#price,#bookid,#binding,#foliox,#folioy,#foliosize,#kucun,#s_id,#catalogue,#introduction,#longPic,#createBy,#description,#bookName)")
    boolean updateBook(Book book);


    @Delete("delete from book where id=#{id}")
    boolean deleteBookById(@Param("id") Long id);

    @Select("select count(*) from book")
    Long selectBookCount();

    @Select("select id,typeId,author,bookid,coverUrl,bookName,kucun,introduction,price from book where typeid=1")
    List<BBook> selectPreviewBookByTypeid(@Param("typeid") Long typeid);


//    @Select("select * from book limit #{start},#{limit}")
    @Select("select b.id,b.typeid,b.isbn,b.id, b.typeid, b.isbn,\n" +
            "b.publishedtime, b.author, b.price,b.coverUrl,\n" +
            "b.bookid, b.binding, b.foliox, b.folioy,\n" +
            "b.foliosize, b.kucun, b.s_id,b.status,\n" +
            "b.catalogue, b.introduction, b.longPic,\n" +
            "b.createtime, b.createBy, b.updatetime, b.updateBy,\n" +
            "b.description, bookName, coverUrl,\n" +
            "bt.key as BookTypeName, bt.label as BookTypeLabel\n" +
            "from book as b left join booktype bt\n" +
            "on b.typeid = bt.id limit #{start},#{limit}")
    List<Book> selectBookList(@Param("start") int start,@Param("limit") int limit);
}
