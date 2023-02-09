package com.example.BookstoreSystem.client.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.entity.Book;
import com.example.BookstoreSystem.client.entity.Booktype;
import com.example.BookstoreSystem.client.mapper.BookMapper;
import com.example.BookstoreSystem.client.service.IBookService;
import com.example.BookstoreSystem.util.JwtUtil;
import com.example.BookstoreSystem.util.TokenUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.List;

/**
 *书籍控制器
 * @author yefeng
 * @since 2022-04-06
 */
@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {

    @Resource
    BookMapper mapper;

    /**
     * @param request 请求
     * @return {@link Result }
     * @throws
     * @title getAllBookType
     * @author yefeng
     * @description TODO 获取所有书的类型
     * @updateTime 2022/05/31
     */
    @GetMapping("/getAllBookType")
    public Result getAllBookType(HttpServletRequest request){
        String token = TokenUtil.getToken(request);
        String[] roles = JwtUtil.getRoles(token);
        boolean flag=JwtUtil.hasRole(request,"ROLE_ADMIN");
        if(!flag){
            return Result.failure("没有管理员权限");
        }
        try {
            List<Booktype> allBooktype = mapper.findALLBookType();
            return new Result().setErrCode(200).setErrMsg("成功获取书籍类型").setData(allBooktype);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().setErrCode(500).setErrMsg("获取书籍类型失败");
        }
    }


    /**
     * @return {@link Result }
     * @throws
     * @title getAllPreviewBook
     * @author yefeng
     * @description TODO 获取书的预览（已经分类，用于首页）
     * @updateTime 2022/05/31
     */
    @GetMapping("/getAllPreviewBook")
    public Result getAllPreviewBook(){
        HashMap<String, Object> list=service.getAllPreviewBook();

        if (!list.isEmpty()){
            return Result.success().setData(list);
        }
        return Result.failure("获取书本失败");
    }




    @Resource
    IBookService service;


    /**
     * @param request 请求
     * @return {@link Result }
     * @throws
     * @title getAllBook
     * @author yefeng
     * @description TODO 获取所有数据需要管理员权限
     * @updateTime 2022/05/31
     */
    @GetMapping("/getAllBook")
    public Result getAllBook(HttpServletRequest request){
        boolean flag = JwtUtil.hasRole(request, "ROLE_ADMIN");
        if (!flag){
            return Result.failure("没有管理员权限");
        }
        JSONObject allBook = service.getAllBook();
        return new Result().setErrCode(200).setErrMsg("成功获取书籍").setData(allBook);

//        System.out.println(map);
//        System.out.println(new JSONObject(map));
//        System.out.println(responseMsg.toJsonObject().toJSONString());
    }


    /**
     * @param page  页面
     * @param limit 限制
     * @return {@link Result }
     * @throws
     * @title getBookByPage
     * @author yefeng
     * @description TODO 分页获取书不要管理员权限
     * @updateTime 2022/05/31
     */
    //获取书列表分页 不要管理员权限 分页
    @CrossOrigin
    @GetMapping("/getBookList")
    public Result getBookByPage( @Min(value = 1,message = "最小值应为1") int page, @RequestParam("limit") @Max(20) int limit){
        List<Book> bookArrayList=service.getBookList(page,limit);
        if (bookArrayList.isEmpty()){
            return Result.failure("获取书本失败");
        }
        JSONObject json = new JSONObject();
        json.put("items", bookArrayList);
        json.put("total", service.getBookCount());
        return Result.success().setData(json);
    }


    /**
     * @param book 书
     * @return {@link Result }
     * @title addBook
     * @author yefeng
     * @description TODO 添加书
     * @updateTime 2022/05/25
     */
    @PostMapping("/addBook")
    public Result addBook(@RequestBody Book book){
        System.out.println(book);
       boolean flag= service.addBook(book);
        if (flag) {
            return Result.success();
        }
        return Result.fail("添加图书失败");
    }


    /**
     * @param book 书
     * @return {@link Result }
     * @title updateBook
     * @author yefeng
     * @description TODO 修改书
     * @updateTime 2022/05/25
     */
    @PutMapping("/updateBook")
    public Result updateBook(@RequestBody Book book){
       boolean flag= service.updateBook(book);
        if (flag) {
            return Result.success();
        }
        return Result.fail("修改书籍失败");

    }

    /**
     * @param id id
     * @return {@link Result }
     * @title deleteBook
     * @author yefeng
     * @description TODO 删除书本
     * @updateTime 2022/05/25
     */
    @DeleteMapping("/deleteBook/{id}")
    public Result deleteBook(@PathVariable Long id){
        boolean flag=service.removeById(id);
        if (flag){
            return Result.success();
        }
        return Result.failure("删除书籍失败");
    }

    /**
     * @return {@link Result }
     * @throws
     * @title getBookCount
     * @author yefeng
     * @description TODO 获取书的数量
     * @updateTime 2022/05/31
     */
    @GetMapping("/getbookCount")
    public Result getBookCount(){
        Long count= service.getBookCount();

        return Result.success(count);

    }

}

