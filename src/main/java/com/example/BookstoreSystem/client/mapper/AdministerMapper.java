package com.example.BookstoreSystem.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.BookstoreSystem.client.entity.Administer;

import java.util.List;

/**
 * 
 *  Mapper 接口
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface AdministerMapper extends BaseMapper<Administer> {

   List<Administer> getAdvertiseListByPage(Integer start, Integer size,String sortKey,String sortValue
   ,Integer status,String searchKey);

   Administer selectAll(Integer id);
}
