package com.example.BookstoreSystem.client.controller;


import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.entity.Permission;
import com.example.BookstoreSystem.client.service.IPermissionService;
import com.example.BookstoreSystem.util.LogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 *  权限表
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {


    @Resource
    IPermissionService permissionService;


    @RequestMapping("/generateRoutes")
    public Result generateRoutes(String []Roles) {


        List<Permission> permissions = permissionService.generateRoutes(Roles);

        if (permissions.size() > 0) {
            return Result.success(permissions);
        }
        return Result.failure("没有获取到权限信息");
        //        return null;
    }

    //获取所有权限
    @RequestMapping("/getAllPermission")
    public Result getAllPermission() {
        List<Permission> permissions = permissionService.getAllPermission();
        if (permissions.size() > 0) {
            return Result.success(permissions);
        }
        return Result.failure("没有获取到权限信息");
    }

    //添加权限
    @RequestMapping("/addPermission")
    public Result addPermission(Permission permission) {

        LogUtil.info(permission);
        Boolean flag = permissionService.addPermission(permission);
        if (flag) {
            return Result.success();
        }
        return Result.failure("添加失败");
    }
    //删除权限
    @RequestMapping("/deletePermission/{id}")
    public Result deletePermission(@PathVariable("id") int id) {

        LogUtil.info(id);
        boolean flag = permissionService.deletePermission(id);
        if (flag) {
            return Result.success();
        }
        return Result.failure("删除失败");
    }

    //修改权限
    @PutMapping("/updatePermission")
    public Result updatePermission(@RequestBody Permission permission) {

        LogUtil.info(permission);
        boolean flag = permissionService.updatePermission(permission);
        if (flag) {
            return Result.success();
        }
        return Result.failure("修改失败");
    }
}

