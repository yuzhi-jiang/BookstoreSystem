package com.example.BookstoreSystem.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.BookstoreSystem.client.entity.Role;

import java.util.List;

/**
 * 
 *  角色表,服务类
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface IRoleService extends IService<Role> {

    List<Role> queryAllRoles();



    Role queryRolesById(Integer userId);


    Boolean deleteRole(Integer id);

    Boolean updateRole(Role role);

    Boolean addRole(Role role);


}
