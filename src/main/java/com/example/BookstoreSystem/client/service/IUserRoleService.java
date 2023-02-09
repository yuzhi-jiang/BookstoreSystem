package com.example.BookstoreSystem.client.service;

import com.example.BookstoreSystem.client.bean.BUserRole;
import com.example.BookstoreSystem.client.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 
 *  服务类
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface IUserRoleService extends IService<UserRole> {

    Boolean deleteUserRole(Integer rid);

    Boolean updateUserRole(BUserRole userRole);

    Boolean addUserRole(BUserRole userRole);

    List<UserRole> getAllUserRole();
}
