package com.example.BookstoreSystem.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.BookstoreSystem.client.entity.AdvertiseType;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName AdvertiseTypeMapper.java
 * @Description TODO
 * @createTime 2022年05月08日 14:17:00
 */
public interface AdvertiseTypeMapper extends BaseMapper<AdvertiseType> {

    @Select("select * from advertisetype where id = #{id}")
    AdvertiseType getAdvertiseTypeById(Integer id);

    @Select("select key,label from advertisetype where id = #{id}")
    String getAdvertiseTypeNameById(@Param("id") Integer id);

    @Select("select * from advertisetype")
    List<AdvertiseType> queryAllAdvertiseType();


    @Select("select * from advertisetype limit #{start},#{pageSize}")
    List<AdvertiseType> queryAdvertiseTypeByPage(int start, int pageSize);

    @Select("select count(*) from advertisetype")
    Integer quertAdvertiseTYpeCount();

    @Update("update advertisetype set label = #{label},`key` = #{key},status=#{status},updateBy=#{updateUserId},description=#{description},updatetime=current_timestamp where id = #{id}")
    Boolean updateAdvertiseType(AdvertiseType advertiseType);

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("insert into advertisetype(label,`key`,status,createBy,description) values(#{label},#{key},#{status},#{createUserId},#{description})")
//    @Insert("insert into advertisetype(label,`key`,status,createBy,description) values('推广类型','dd',1,1,'推广类型');")
    Integer addAdvertiseType(AdvertiseType advertiseType);

    @Delete("delete from advertisetype where id = #{id}")
    Boolean deleteAdTypeById(@Param("id") Integer id);
}
