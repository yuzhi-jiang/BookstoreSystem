package com.example.BookstoreSystem.client.controller;


import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.entity.Store;
import com.example.BookstoreSystem.client.service.IStoreService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 店铺表
 *
 * @author yefeng
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/store")
public class StoreController {
    @Resource
    IStoreService iStoreService;

    //获取所有店铺信息
    @GetMapping("/getAllStore")
    public Result getAllStore() {
        List<Store> list = iStoreService.getAllStore();
        if (list != null) {
            return Result.success(list);
        }
        return Result.fail("查询失败");
    }

    //添加店铺
    @PostMapping("/addStore")
    public Result addStore(@RequestBody Store store) {
        Boolean flag = iStoreService.addStore(store);
        if (flag) {
            return Result.success("添加成功");
        }
        return Result.fail("添加失败");
    }

    //修改店铺
    @PostMapping("/updateStore")
    public Result updateStore(@RequestBody Store store) {
        Boolean flag = iStoreService.updateStore(store);
        if (flag) {
            return Result.success("修改成功");
        }
        return Result.fail("修改失败");
    }

    //删除店铺
    @Delete("/deleteStore/{id}")
    public Result deleteStore(@PathVariable("id") int id){
        Boolean flag = iStoreService.deleteStore(id);
        if (flag) {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    //根据店铺id查询店铺信息
    @GetMapping("/getStoreById/{id}")
    public Result getStoreById(@PathVariable("id") Integer id) {
        Store store = iStoreService.getStoreById(id);
        if (store != null) {
            return Result.success(store);
        }
        return Result.fail("查询失败");
    }

    //根据店铺名称查询店铺信息
    @GetMapping("/getStoreByName/{name}")
    public Result getStoreByName(@PathVariable("name") String name) {
        Store store = iStoreService.getStoreByName(name);
        if (store != null) {
            return Result.success(store);
        }
        return Result.fail("查询失败");
    }

    //删除店铺
    @RequestMapping("/deleteStoreById/{id}")
    public Result deleteStoreById(@PathVariable("id") int id){
        Boolean flag = iStoreService.deleteStoreById(id);
        if (flag) {
            return Result.success();
        }
        return Result.fail("删除失败");
    }


}

