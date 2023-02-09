package com.example.BookstoreSystem.client.controller;


import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.entity.Administer;
import com.example.BookstoreSystem.client.mapper.AdministerMapper;
import com.example.BookstoreSystem.client.service.impl.AdministerService;
import com.example.BookstoreSystem.util.LogUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yefeng
 * @version 1.0.0
 * @className com.example.BookstoreSystem.client.controller.AdministerController
 * @Description TODO
 * @createTime 2022/05/10
 * @SEE
 * @Doc 前端控制器
 */
@RestController
@RequestMapping("/administer")
public class AdministerController {


    @Resource
    AdministerService service;
    @Resource
    AdministerMapper mapper;


    // getAdvertiseList?page=1&size=10&sort=+id&status=1&keyword='q2'&createTimeStart=2020-05-10&createTimeEnd&updateTime=2020-05-10

    /**
     * @title getAdvertiseListByPage
     * @description 获取广告列表，分页，排序，模糊查询，限制条数
     * @author yefeng
     * @Param
     * @updateTime 2022/5/10 23:17
     */
    @RequestMapping("/getAdvertiseListByPage")
    public Result getAdvertiseListByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "1") Integer limit
            , @RequestParam(value = "sort", defaultValue = "+id") String sort
            , @RequestParam(value = "status", defaultValue = "1") Integer status
            , @RequestParam(value = "keyword", defaultValue = "") String keyword
    ) {
        String sortKey = sort.substring(1);
        String sortValue = sort.charAt(0) == '-' ? "desc" : "asc";//desc升序，asc降序
        mapper.getAdvertiseListByPage(page, limit, sortKey,sortValue, status, keyword);
        return null;
    }


    /**
     * @return Result
     * @title getAllAdvertiseList
     * @description 获取所有广告列表
     * @author yefeng
     * @Param
     * @updateTime 2022/5/10 23:17
     */
    @RequestMapping("/getAllAdvertiseList/{id}")
    public Result getAllAdvertiseList(@PathVariable("id") Integer id) {
        Administer administers = mapper.selectAll(id);
        LogUtil.info("administers = " + administers);
        return Result.success(administers);
    }
}

