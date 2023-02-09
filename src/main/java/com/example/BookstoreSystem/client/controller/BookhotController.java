package com.example.BookstoreSystem.client.controller;


import com.example.BookstoreSystem.bean.Result;
import com.example.BookstoreSystem.client.entity.Book;
import com.example.BookstoreSystem.client.entity.Bookhot;
import com.example.BookstoreSystem.client.service.IBookhotService;
import com.example.BookstoreSystem.util.TokenUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * 书的热度控制器
 *
 * @author yefeng
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/bookhot")
public class BookhotController {
    @Resource
    IBookhotService bookhotService;

    //获取所有书的热度
    public Result getAllBookhot() {
        List<Book> list = bookhotService.getAllBookHot();
        return null;
    }

    public Result getBookHotByPage(@RequestParam("page") @Min(1) int currPage, @RequestParam("limit") @Max(20) int pageSize) {

        List<Book> list = bookhotService.getBookHotByPage(currPage, pageSize);
        return null;
    }

    //获取热度前十的书
    public Result getTopTenBookhot() {
        List<Book> list = bookhotService.getTopTenBookHot();


        return null;
    }
    //修改书的热度

    public Result updateBookhot(String bookId, String hotNum, HttpServletRequest request) {

        String updateUserId = TokenUtil.getUserId(request);
        Bookhot bookhot = new Bookhot();
        bookhot.setBookid(Integer.valueOf(bookId));

        bookhot.setHotnum(Integer.valueOf(hotNum));

        bookhot.setUpdateby(Integer.valueOf(updateUserId));

       Boolean falg= bookhotService.updateBookHot(bookhot);

        return null;
    }

}

