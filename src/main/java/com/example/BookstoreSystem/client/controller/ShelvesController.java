package com.example.BookstoreSystem.client.controller;


import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.entity.Shelves;
import com.example.BookstoreSystem.client.service.IShelvesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 *  货架表
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
@Controller
@RequestMapping("/shelves")
public class ShelvesController {


    //货架表


    @Resource
    IShelvesService shelvesService;

//    getAllShelves
    @RequestMapping("/getAllShelves")
      public Result getAllShelves() {
         List<Shelves> shelvesList= shelvesService.getAllShelves();
         return Result.success(shelvesList);
    }
//      getShelfByPage
      @RequestMapping("/getShelfByPage")
      public Result getShelfByPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
          List<Shelves> shelvesList = shelvesService.getShelfByPage(page, size);
          if (shelvesList != null) {
              return Result.success(shelvesList);
          }
          return Result.failure().setErrCode(400).setErrMsg("查询货架失败");
      }

//    getShelvesById
    @RequestMapping("/getShelvesById/{id}")
    public Result getShelvesById(@PathVariable("id") Integer id) {
        Shelves shelves = shelvesService.getShelvesById(id);
        if (shelves != null) {
            return Result.success(shelves);
        }
        return Result.failure().setErrCode(400).setErrMsg("查询货架失败");
    }

//    getShelvesByName
    @RequestMapping("/getShelvesByName")
    public Result getShelvesByName(@RequestParam("name") String name) {
        Shelves shelves = shelvesService.getShelvesByName(name);
        if (shelves != null) {
            return Result.success(shelves);
        }
        return Result.failure().setErrCode(400).setErrMsg("查询货架失败");
    }

//    addShelves
    @PostMapping("/addShelves")
    public Result addShelves(@RequestBody Shelves shelves) {//@RequestBody是将前端传过来的json数据转换成对象
        Boolean flag= shelvesService.addShelves(shelves);//数据库sql语句不够完善，暂时不用
        if (flag) {
            return Result.success(shelves.getId());
        }
        return Result.failure().setErrCode(400).setErrMsg("添加货架失败");
    }
//    updateShelves
    @PutMapping("/updateShelves")
    public Result updateShelves(@RequestBody Shelves shelves) {
        Boolean flag= shelvesService.updateShelves(shelves);//数据库sql语句不够完善，暂时不用
        if (flag) {
            return Result.success(shelves.getId());
        }
        return Result.failure().setErrCode(400).setErrMsg("更新货架失败");
    }

//    deleteShelves
    @DeleteMapping("/deleteShelves/{id}")
    public Result deleteShelves(@PathVariable("id") Integer id) {
        if (id == null) {
            return Result.failure().setErrCode(401).setErrMsg("参数错误");
        }
        Boolean flag= shelvesService.deleteShelves(id);
        if (flag) {
            return Result.success(id).setErrMsg(id+"删除货架成功");
        }
        return Result.failure().setErrCode(400).setErrMsg("删除货架失败");
    }

}

