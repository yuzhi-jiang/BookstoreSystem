package com.example.BookstoreSystem.client.mapper;

import com.example.BookstoreSystem.client.bean.BUserRole;
import com.example.BookstoreSystem.client.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
public interface UserRoleMapper extends BaseMapper<UserRole> {

    Boolean updateUserRole(BUserRole userRole);

    Boolean addUserRole(BUserRole userRole);

    List<UserRole> queryAllUserRole();
}
