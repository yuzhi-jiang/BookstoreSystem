package com.example.BookstoreSystem.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.BookstoreSystem.client.entity.Advertise;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

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
public interface AdvertiseMapper extends BaseMapper<Advertise> {




    @Select("select * from advertise")
    List<Advertise> queryAllAdvertise();


    //分页查询
    @Select("select a.id,adName,a.status,typeid,sizex,sizey,link,picpath,a.createtime,a.description,a.createBy,a.updatetime,a.updateBy,`key` as typeName,label as typeText from advertise as a left join advertisetype at on at.id = a.typeid limit #{start},#{pageSize}")
//    @Result(column = "typeid",property = "typeName",
//        one = @One(select = "com.example.BookstoreSystem.client.mapper.AdvertiseTypeMapper.getAdvertiseTypeNameById",
//        fetchType = FetchType.EAGER))
    List<Advertise> queryAdvertiseByPage(@Param("start") int start, @Param("pageSize") int pageSize);

    //查询总记录数
    @Select("select count(*) from advertise")
    int queryAdvertiseCount();


    @Select("select * from advertise where typeid = (select id from advertisetype where `key` = #{typename})")
    List<Advertise> queryAllAdvertiseByType(@Param("typename") String type);

    @Select("select * from advertise where id = #{id}")
    @Results(
            @Result(column = "typeid",property = "typeName",
                    one = @One(
                            select = "com.example.BookstoreSystem.client.mapper.AdvertiseTypeMapper.queryAdvertiseTypeById",
                            fetchType = FetchType.EAGER)
            )
    )
    Advertise queryAdvertiseById(@Param("id") Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("insert into advertise(adName,typeid,sizex,sizey,link,picpath,status,description) values(#{adName},#{typeid},#{sizex},#{sizey},#{link},#{picpath},#{status},#{description})")
    Integer insertAdvertise(Advertise advertise);


    @Update("update advertise set adName=#{adName},typeid=#{typeid},sizex=#{sizex},sizey=#{sizey},link=#{link},picpath=#{picpath},description=#{description},status=#{status},updatetime=current_timestamp where id=#{id}")
    Boolean updateAdvertise(Advertise advertise);


    @Delete("delete from advertise where id = #{id}")
    Boolean deleteAdvertise(@Param("id") Integer id);


}
