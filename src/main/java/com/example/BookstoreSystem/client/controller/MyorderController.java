package com.example.BookstoreSystem.client.controller;


import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.entity.Myorder;
import com.example.BookstoreSystem.client.service.IMyorderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 *  我的订单
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/myorder")
public class MyorderController {

    @Resource
    IMyorderService service;
//获取我的订单
    public Result getMyOrder(){
        List<Myorder> myorders=service.list();
        if (myorders.isEmpty()){
            return Result.failure("没有订单");
        }
        return Result.success(myorders);
    }
//添加订单
    @PostMapping("/addOrder")
    public Result addMyOrder(@RequestBody  Myorder myorder){
        return null;
    }
//删除订单
    public Result deleteMyOrder(){
        return null;
    }
//修改订单
    public Result updateMyOrder(){
        return null;
    }

//取消订单
    public Result cancelMyOrder(){
        return null;
    }
//查看订单
//查看订单详情
    public Result getMyOrderDetail(String orderId){
        return null;
    }
}

