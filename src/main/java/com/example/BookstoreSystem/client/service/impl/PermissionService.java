package com.example.BookstoreSystem.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.BookstoreSystem.client.entity.Permission;
import com.example.BookstoreSystem.client.mapper.PermissionMapper;
import com.example.BookstoreSystem.client.service.IPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 *  服务实现类
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
@Service
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> generateRoutes(String[] roles) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("name", roles);

        List<Permission> permissions = permissionMapper.selectList(queryWrapper);
        return permissions;

    }

    @Override
    public List<Permission> getAllPermission() {
        return null;
    }

    @Override
    public Boolean addPermission(Permission permission) {
        return null;
    }

    @Override
    public boolean updatePermission(Permission permission) {
        return false;
    }

    @Override
    public boolean deletePermission(int id) {
        return false;
    }
}
