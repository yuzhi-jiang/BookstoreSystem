package com.example.BookstoreSystem.client.controller;


import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.entity.Shoppingcart;
import com.example.BookstoreSystem.client.service.IShoppingCartService;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物粗
 *
 * @author yefeng
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    @Resource
    IShoppingCartService service;

    //getShoppingCartById
    @RequestMapping("/getShoppingCartById/{id}")
    public Result getShoppingCartById(@PathVariable("id") Long id) {
        Shoppingcart shoppingcart = service.getShoppingCartById(id);
        if (shoppingcart != null) {
            return Result.success(shoppingcart);
        }
        return Result.failure("查询购物车失败");
    }

    //getShoppingCartByUserId
    @RequestMapping("/getShoppingCartByUserId/{userId}")
    public Result getShoppingCartByUserId(@PathVariable("userId") Long userId) {

        List<Shoppingcart> shoppingcarts = service.getShoppingCartByUserId(userId);
        if (shoppingcarts != null) {
            return Result.success(shoppingcarts);
        }
        return Result.failure("查询购物车失败");

    }

    //addShoppingCart //添加购物车
    @PostMapping("/addShoppingCart")
    public Result addShoppingCart(Shoppingcart shoppingCart) {
        Boolean falg = service.addShoppingCart(shoppingCart);
        if (falg) {
            return Result.success("添加购物车成功");
        }
        return Result.failure("添加购物车失败");
    }

    //addShoppingCartByList //添加购物车列表
    @PostMapping("/addShoppingCartByList")
    public Result addShoppingCartByList(@RequestBody List<Shoppingcart> shoppingCartList) {

       Integer number= service.addShoppingCartByList(shoppingCartList);
       if(number<=0){
           return Result.failure("添加购物车失败");
       }

       //后台如果添加失败，则返回失败，一条都不添加，则注释下面代码
        if(number!=shoppingCartList.size()){
            //需要进行回滚 ?
            return Result.failure("添加购物车出现意外错误");

        }
        return Result.success("添加购物车成功");
    }

    //deleteShoppingCart //删除购物车
    @RequiresPermissions("shoppingcart:delete")
    @Delete("/deleteShoppingCart/{id}")
    public Result deleteShoppingCart(@PathVariable("id") Long id) {
       Boolean flag= service.deleteShoppingCart(id);
       if(flag){
           return Result.success("删除购物车成功");
       }
       return Result.failure("删除购物车失败");
    }

    //updateShoppingCart //更新购物车
    @PostMapping("/updateShoppingCart")
    public Result updateShoppingCart(@RequestBody Shoppingcart shoppingCart) {
       Boolean flag= service.updateShoppingCart(shoppingCart);
       if(flag){
           return Result.success("更新购物车成功");

       }
       return Result.failure("更新购物车失败");
    }

    //deleteAllShoppingCart //删除所有购物车
    @RequiresPermissions("shoppingcart:delete")
    @Delete("/deleteAllShoppingCart/{userId}")
    public Result deleteAllShoppingCart(@PathVariable("userId") Long userId) {
        Boolean flag= service.deleteAllShoppingCart(userId);
        if(flag){
            return Result.success("删除所有购物车成功");
        }
        return Result.failure("删除所有购物车失败");
    }

}

