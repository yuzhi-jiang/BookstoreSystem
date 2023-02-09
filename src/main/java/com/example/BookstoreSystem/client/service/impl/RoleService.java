package com.example.BookstoreSystem.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.BookstoreSystem.client.entity.Role;
import com.example.BookstoreSystem.client.mapper.RoleMapper;
import com.example.BookstoreSystem.client.service.IRoleService;
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
public class RoleService extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    RoleMapper mapper;

    @Override
    public List<Role> queryAllRoles() {
        return mapper.queryAllRoles();
    }

    @Override
    public Role queryRolesById(Integer id) {
        return mapper.selectById(id);
    }

//    @Override
//    public List<Role> queryRolesByIds(List<Integer> ids) {
//        return mapper.queryRolesByIds(ids);
//    }

    @Override
    public Boolean deleteRole(Integer id) {
        return mapper.deleteById(id) > 0;
    }

    @Override
    public Boolean updateRole(Role role) {
        return mapper.updateRole(role);
    }

    @Override
    public Boolean addRole(Role role) {
        return mapper.AddRole(role);
    }
}
