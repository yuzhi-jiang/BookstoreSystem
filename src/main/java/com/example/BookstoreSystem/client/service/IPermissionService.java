package com.example.BookstoreSystem.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.BookstoreSystem.client.entity.Permission;

import java.util.List;

/**
 * 
 *  服务类
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface IPermissionService extends IService<Permission> {

    List<Permission> generateRoutes(String[] roles);

    List<Permission> getAllPermission();

    Boolean addPermission(Permission permission);

    boolean updatePermission(Permission permission);

    boolean deletePermission(int id);
}
