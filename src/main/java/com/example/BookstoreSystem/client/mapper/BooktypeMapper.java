package com.example.BookstoreSystem.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface BooktypeMapper extends BaseMapper<Booktype> {

    @Select("select * from booktype")
    List<Booktype> selectAllBookType();


    @Update("update booktype set `key`=#{key},updatetime=current_timestamp,updateby=#{updateby},label=#{label},parentId=#{parentId},status=#{status},description=#{description} where id=#{id}")
    Boolean updateBookTypeById(Booktype entity);


    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("insert into booktype(`key`,createby,label,parentId,status,description) values(#{key},#{createby},#{label},#{parentId},#{status},#{description})")
    Boolean insertBookType(Booktype entity);

    @Delete("delete from booktype where id=#{id}")
    Boolean deleteBookTypeById(Long id);

    @Delete("delete from booktype where parentId=#{id}")
    int deleteBookTypeByParentId(Long id);

    @Select("select * from booktype where parentId=#{id}")
    List<Booktype> selectBookTypeByParentId(Long id);

    @Select("select * from booktype limit #{start},#{limit}")
    List<Booktype> selectAllBookTypeList(int start, int limit);

    @Select("select count(*) from booktype")
    Long selecBookTypeCount();
}
