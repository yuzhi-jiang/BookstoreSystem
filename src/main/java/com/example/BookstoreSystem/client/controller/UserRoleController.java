package com.example.BookstoreSystem.client.controller;


import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.bean.BUserRole;
import com.example.BookstoreSystem.client.entity.UserRole;
import com.example.BookstoreSystem.client.service.IUserRoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * 用户角色表 前端控制器
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Resource
    private IUserRoleService service;

    /**
     * @title getAllUserRole
     * @description 查询所有用户角色
     * @author yefeng
     * @Param
     * @updateTime 2022/5/4 8:54
     */
    @RequestMapping("/getAllUserRole")
    public Result getAllUserRole() {
        List<UserRole> userRoles = service.getAllUserRole();
        if (userRoles != null) {
            return Result.success(userRoles);
        }
        return Result.fail("查询失败");
    }

    /**
     * @title getUserRoleById
     * @description 根据id查询用户角色  权限呢？
     * @author yefeng
     * @Param
     * @updateTime 2022/5/4 8:55
     */
    @RequestMapping("/getUserRoleById")
    public Result getUserRoleById(@RequestParam("uid") Integer uid) {
        UserRole byId = service.getById(uid);
        if (byId != null) {
            return Result.success(byId);
        }
        return Result.fail("查询失败");
    }


    /**
     * @title addUserRole
     * @description 添加用户角色
     * @author yefeng
     * @Param BUserRole
     * @updateTime 2022/5/4 8:57
     */
    @PostMapping("/addUserRole")
    public Result addUserRole(@RequestBody @Validated BUserRole userRole) {
       Boolean flag= service.addUserRole(userRole);
       if (flag) {
           return Result.success("添加成功");
       }
       return Result.fail("添加失败");
    }

    /**
     * @title updateUserRole
     * @description 更新用户角色
     * @author yefeng
     * @Param BUserRole
     * @updateTime 2022/5/4 8:57
     */
    @RequestMapping("/updateUserRole")
    public Result updateUserRole(@RequestBody @Validated BUserRole userRole) {
        Boolean flag= service.updateUserRole(userRole);
        if (flag) {
            return Result.success("修改成功");
        }
        return Result.fail("修改失败");
    }

    /**
     * @title deleteUserRole
     * @description 删除用户角色
     * @author yefeng
     * @Param int rid
     * @updateTime 2022/5/4 8:58
     */
    @RequestMapping("/deleteUserRole")
    public Result deleteUserRole(@RequestParam("role_id") Integer rid) {
        Boolean flag= service.deleteUserRole(rid);
        if (flag) {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }
}

