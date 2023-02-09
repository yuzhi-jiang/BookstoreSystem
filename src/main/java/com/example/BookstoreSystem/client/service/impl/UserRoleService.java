package com.example.BookstoreSystem.client.service.impl;

import com.example.BookstoreSystem.client.bean.BUserRole;
import com.example.BookstoreSystem.client.entity.UserRole;
import com.example.BookstoreSystem.client.mapper.UserRoleMapper;
import com.example.BookstoreSystem.client.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {


    @Resource
    UserRoleMapper mapper;


    @Override
    public Boolean deleteUserRole(Integer rid) {
        return mapper.deleteById(rid)>0;
    }

    @Override
    public Boolean updateUserRole(BUserRole userRole) {
     return    mapper.updateUserRole(userRole);
    }

    @Override
    public Boolean addUserRole(BUserRole userRole) {
        return mapper.addUserRole(userRole);
    }

    @Override
    public List<UserRole> getAllUserRole() {
        return mapper.queryAllUserRole();
    }
}
