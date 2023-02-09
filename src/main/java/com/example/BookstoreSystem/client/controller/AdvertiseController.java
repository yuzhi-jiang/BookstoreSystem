package com.example.BookstoreSystem.client.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.BookstoreSystem.bean.ResCode;
import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.entity.Advertise;
import com.example.BookstoreSystem.client.service.IAdvertiseService;
import com.example.BookstoreSystem.util.Constan2;
import com.example.BookstoreSystem.util.LogUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * 
 * 前端控制器
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/advertise")
public class AdvertiseController {


    @Resource
    IAdvertiseService advertiseService;

//getAllAdvertise

    /**
     * @title getAllAdvertise
     * @description 获取所有广告
     * @author yefeng
     * @Param
     * @updateTime 2022/5/4 1:05
     */
    //需要管理员权限
    @RequestMapping("/getAllAdvertise")
    public Result getAllAdvertise() {
        List<Advertise> advertises = advertiseService.getAllAdvertise();
        if (advertises != null) {
            return Result.success(advertises);
        } else {
            return Result.failure(ResCode.Advertisement_Get_Failed.getErrMessage());
        }
    }

    /**
     * @title getAdvertiseById
     * @description 更加id获取广告信息
     * @author yefeng
     * @Param int id
     * @updateTime 2022/5/4 1:04
     */
    @RequestMapping("/getAdvertiseById")
    public Result getAdvertiseById(@RequestParam("id") int id) {
        Advertise advertise = advertiseService.getAdvertiseById(id);
        if (advertise != null) {
            return Result.success(advertise);
        } else {
            return Result.failure(ResCode.Advertisement_Get_Failed.getErrMessage());
        }
    }
    //addAdvertise


    @PostMapping("/addAdvertise")
    /**
     * @title addAdvertise
     * @description 添加广告
     * @author yefeng
     * @Param
     * @updateTime 2022/5/4 1:04
     */
    public Result addAdvertise(@RequestBody Advertise advertise) {
        LogUtil.info("advertise:" + advertise);
        LogUtil.info(String.valueOf(advertise.getId()));
        advertiseService.addAdvertise(advertise);
        Integer id;
        System.out.println(advertise.getId());
        id=advertise.getId();
        if (id   != null) {
            return Result.success(new JSONObject().fluentPut("id", id));
        } else {
            return Result.failure(ResCode.Advertisement_Add_Failed.getErrMessage());
        }
    }

//updateAdvertise

    /**
     * @title updateAdvertise
     * @description 更新广告信息
     * @author yefeng
     * @Param
     * @updateTime 2022/5/4 1:03
     */
    @PutMapping("/updateAdvertise")
    public Result updateAdvertise(@RequestBody Advertise advertise) {
        LogUtil.info("advertise:" + advertise);
        LogUtil.info("advertise:" + advertise.toString());
//        return Result.success();
        boolean flag = advertiseService.updateAdvertise(advertise);
        LogUtil.info(flag);
        if (flag) {
            return Result.success();
        } else {
            return Result.failure(ResCode.Advertisement_Update_Failed.getErrMessage());
        }
    }
//deleteAdvertise

    /**
     * @title deleteAdvertise
     * @description 根据id删除广告
     * @author yefeng
     * @Param int id
     * @updateTime 2022/5/4 1:02
     */
    @RequiresRoles("ROLE_ADMIN_SUPPER")
//    @RequiresRoles("ROLE_USER")
    @RequestMapping("/deleteAdvertise/{id}")
    public Result deleteAdvertise(@PathVariable("id") int id) {
        LogUtil.info("你想要删除id:" + id);
        if (advertiseService.deleteAdvertiseById(id)) {
            return Result.success();
        } else {
            return Result.failure(ResCode.Advertisement_Delete_Failed.getErrMessage());
        }
    }

    private static final Integer MAX_PAGE_SIZE = 20;
//getAdvertiseByPage

    /**
     * @param currPage 当前页面
     * @param pageSize 页面大小
     * @return {@link Result }
     * @throws
     * @title getAdvertiseTypeByPage
     * @author yefeng
     * @description TODO 获取广告列表（分页）
     * @updateTime 2022/05/20
     */
    @GetMapping("/getAdvertiseByPage")
    public Result getAdvertiseByPage(@RequestParam("page") @Min(1) int currPage, @RequestParam("limit") @Max(20) int pageSize) {
        if (pageSize > Constan2.MAX_PAGE_SIZE) {
            return Result.failure(ResCode.Page_Size_Too_Large.getErrMessage());
        }
        List<Advertise> advertises = advertiseService.getAdvertiseByPage(currPage, pageSize);
        if (advertises != null) {
            JSONObject json = new JSONObject();
            json.put("items", advertises);
            json.put("total", advertiseService.getAdvertiseCount());
            return Result.success(json);
        }
        return Result.failure("获取广告列表失败");
    }

    //getAdvertiseCount

    /**
     * @title getAdvertiseCount
     * @description 获取广告总数
     * @author yefeng
     * @Param Result
     * @updateTime 2022/5/4 0:59
     */
    @RequestMapping("/getAdvertiseCount")
    public Result getAdvertiseCount() {


        int count = advertiseService.getAdvertiseCount();
        if (count != 0) {
            return Result.success(count);
        } else {
            return Result.failure(ResCode.Advertisement_Get_Failed.getErrMessage());
        }
    }

}





