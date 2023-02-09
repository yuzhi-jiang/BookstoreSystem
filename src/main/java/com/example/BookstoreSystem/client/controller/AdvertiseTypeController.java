package com.example.BookstoreSystem.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.BookstoreSystem.bean.ResCode;
import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.entity.AdvertiseType;
import com.example.BookstoreSystem.client.service.IAdvertiseTypeService;
import com.example.BookstoreSystem.util.Constan2;
import com.example.BookstoreSystem.util.JwtUtil;
import com.example.BookstoreSystem.util.LogUtil;
import com.example.BookstoreSystem.util.TokenUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName AdvertiseTypeController.java
 * @Description TODO
 * @createTime 2022年05月17日 21:12:00
 */
@RestController
@RequestMapping("/advertiseType")
public class AdvertiseTypeController {

    @Resource
    IAdvertiseTypeService advertiseTypeService;

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
    public Result getAdvertiseTypeByPage(@RequestParam("page") @Min(1) int currPage, @RequestParam("limit") @Max(20) int pageSize) {
        if (pageSize > Constan2.MAX_PAGE_SIZE) {
            return Result.failure(ResCode.Page_Size_Too_Large.getErrMessage());
        }
        List<AdvertiseType> advertises = advertiseTypeService.getAdvertiseTypeByPage(currPage, pageSize);
        if (advertises != null) {
            JSONObject json = new JSONObject();
            json.put("items", advertises);
            json.put("total", advertiseTypeService.getAdvertiseTypeCount());
            return Result.success(json);
        }
        return Result.failure(ResCode.AdvertiseType_Get_Failed.getErrMessage());
    }


    //添加广告类型
    @PostMapping("/addAdvertiseType")
    public Result addAdvertiseType(@RequestBody AdvertiseType advertiseType, HttpServletRequest request) {
        String token = TokenUtil.getToken(request);
        String userId = JwtUtil.getUserIdByToken(token);
        if (userId == null) {
            return Result.failure("用户未登录");
        }
        LogUtil.info(userId);
        advertiseType.setCreateUserId(Integer.valueOf(userId));
        LogUtil.info(advertiseType);
        if (advertiseTypeService.addAdvertiseType(advertiseType)) {
            return Result.success(new JSONObject().fluentPut("id", advertiseType.getId()));
        }
        return Result.failure(ResCode.AdvertiseType_Add_Failed.getErrMessage());
    }

    //删除广告类型
    @RequestMapping("/deleteAdvertiseType/{id}")
    public Result deleteAdType(@PathVariable("id") int id) {
        if (advertiseTypeService.deleteAdvertiseType(id)) {
            return Result.success();
        }
        return Result.failure(ResCode.AdvertiseType_Delete_Failed.getErrMessage());
    }

    //修改广告类型
    @PutMapping("/updateAdvertiseType")
    public Result UpdateAdvertiseType(@RequestBody AdvertiseType advertiseType,HttpServletRequest request) {
        String token = TokenUtil.getToken(request);
        String userId = JwtUtil.getUserIdByToken(token);
        if (userId == null) {
            return Result.failure("用户未登录");
        }
        LogUtil.info(userId);
        advertiseType.setUpdateUserId(Integer.valueOf(userId));
        boolean flag = advertiseTypeService.updateAdvertiseType(advertiseType);
        if (flag) {
            return Result.success();
        }
        return Result.failure(ResCode.AdvertiseType_Update_Failed.getErrMessage());
    }
}
