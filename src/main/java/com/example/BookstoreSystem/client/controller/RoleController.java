package com.example.BookstoreSystem.client.controller;


import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.entity.Role;
import com.example.BookstoreSystem.client.service.impl.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 角色表
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    RoleService service;
    /**
     * @title getRoles
     * @description 获取所有的角色
     * @author yefeng
     * @Param
     * @updateTime 2022/5/1 15:35
     */
    @RequestMapping("/getAllRoles")
    public Result getRoles() {

        List<Role> roles = service.queryAllRoles();
        if (roles == null) {
            return Result.fail("没有角色");
        }
        return Result.success("获取角色成功").setData(roles);
    }

    @RequestMapping("/getRoleById")
    public Result getRoleById(Integer id) {

        Role role = service.queryRolesById(id);
        if (role == null) {
            return Result.fail("没有该角色");
        }
        return Result.success("获取角色成功").setData(role);
    }

    @RequestMapping("/addRole")
    public Result addRole(Role role) {

        Boolean flag = service.addRole(role);
        if (!flag) {
            return Result.fail("添加角色失败");
        }
        return Result.success("添加角色成功");
    }

    @RequestMapping("/updateRole")
    public Result updateRole(Role role) {

        Boolean flag = service.updateRole(role);
        if (!flag) {
            return Result.fail("修改角色失败");
        }
        return Result.success("修改角色成功");
    }

    @RequestMapping("/deleteRole")
    public Result deleteRole(Integer id) {

        Boolean falg = service.deleteRole(id);
        if (!falg) {
            return Result.fail("删除角色失败");
        }
        return Result.success("删除角色成功");
    }


}

