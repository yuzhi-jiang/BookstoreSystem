package com.example.BookstoreSystem.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.BookstoreSystem.client.entity.Shelves;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 
 *  Mapper 接口
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface ShelvesMapper extends BaseMapper<Shelves> {
    @Select("select * from shelves")
    List<Shelves> queryAllShelves();

    @Select("select * from shelves limit #{start},#{size}")
    List<Shelves> queryShelfByPage(Integer start, Integer size);

    @Select("select * from shelves where id = #{id}")
    Shelves queryShelvesById(Integer id);

    @Select("select * from shelves where name = #{name}")
    Shelves queryShelvesByName(String name);

    @Update("update shelves set name = #{name},s_id=#{sId} where id = #{id}")
    Boolean updateShelves(Shelves shelves);

    @Insert("insert into shelves(name,s_id) values(#{name},#{sId})")
    Boolean addShelves(Shelves shelves);

    @Delete("delete from shelves where id = #{id}")
    Boolean deleteShelves(Integer id);

    //query the count of shelves
    @Select("select count(*) from shelves")
    Integer queryShelvesCount();
}
