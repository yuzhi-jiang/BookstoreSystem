package com.example.BookstoreSystem.client.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.entity.Booktype;
import com.example.BookstoreSystem.client.service.IBooktypeService;
import com.example.BookstoreSystem.util.JwtUtil;
import com.example.BookstoreSystem.util.TokenUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * 书的类型
 *
 * @author yefeng
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/booktype")
public class BooktypeController {
    @Resource
    IBooktypeService service;

    //获取所有书的类型
    @GetMapping("/getAllBookType")
    public Result getAllBookType() {
        List<Booktype> booktypes = service.getAllBookType();

        System.out.println(booktypes);
        if (!booktypes.isEmpty()) {
           return Result.success().setData(booktypes);
        }
        return Result.failure("获取图书类型失败");
    }


    /**
     * @param page  页面
     * @param limit 限制
     * @return {@link Result }
     * @throws
     * @title getAllBookTypeList
     * @author yefeng
     * @description TODO 获取所有书的类型
     * @updateTime 2022/06/08
     */
    @GetMapping("/getAllBookTypeList")
    public Result getAllBookTypeList(@RequestParam("page") @Min(1) int page, @RequestParam("limit") @Max(20) int limit) {
        List<Booktype> booktypes = service.getAllBookTypeList(page,limit);
        if (!booktypes.isEmpty()) {
            Long count= service.getBookTypeCount();
            JSONObject json = new JSONObject();
            json.put("items", booktypes);
            json.put("total", count);
            System.out.println(booktypes);
            return Result.success().setData(json);
        }
        return Result.failure("获取图书类型失败");
    }

    //添加书的类型
//    @RequiresPermissions("booktype:create") //需要有添加书类型的权限
    @PostMapping("/addBookType")
    public Result addBookType(@RequestBody Booktype booktype, HttpServletRequest request) {
        System.out.println(booktype);
        String userId = JwtUtil.getUserIdByToken(TokenUtil.getToken(request));
        System.out.println(userId);
        booktype.setCreateby(Integer.valueOf(userId));
        boolean flag = service.addBookType(booktype);
        if (flag) {
            return Result.success(new JSONObject().fluentPut("id", booktype.getId()));
        }
        return Result.failure("添加书类型失败");
    }

    //删除书的类型
    @DeleteMapping("/deleteBookType/{id}")
    public Result deleteBookType(@PathVariable Long id) {
        boolean flag = service.removeById(id);
        if (flag) {
            return Result.success();
        }
        return Result.failure("删除书类型失败");
    }

    //修改书的类型
    @PutMapping("/updateBookType")
    public Result updateBookType(@RequestBody Booktype booktype) {
        boolean flag=service.updateBookTypeById(booktype);
        if (flag) {
            return Result.success();
        }
        return Result.failure("修改书类型失败");
    }

    //获取某一类型的书
    @GetMapping("/getBookByType/{parentId}")
    public Result getBookType(@PathVariable Long parentId) {

        List<Booktype> booktypes= service.getBookTypeByType(parentId);
        if (!booktypes.isEmpty()) {
            return Result.success().setData(booktypes);
        }
        return Result.failure("获取书类型失败");
    }

}

