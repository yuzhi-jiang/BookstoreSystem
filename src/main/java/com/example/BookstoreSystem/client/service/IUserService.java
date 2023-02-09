package com.example.BookstoreSystem.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.BookstoreSystem.bean.ResCode;
import com.example.BookstoreSystem.client.bean.BUser;
import com.example.BookstoreSystem.client.bean.MyUserDetail;
import com.example.BookstoreSystem.client.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 *  服务类
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface IUserService extends IService<User> {



    public MyUserDetail getUserDetail(String username);

    ResCode register(BUser user);

    ResCode canRegisterByUserName(String name, String phone, String mail);


    @Transactional
    Integer deleteUser(Long usrId);

    List<MyUserDetail> getAllUser();

    Integer updateUserPwd(Long uid, String password);
    Integer updateUserPwd(String  userName, String password);

    Integer updateUser(MyUserDetail user);

    ResCode checkUser(String userName);

    ResCode checkPhone(String phone);

    ResCode checkMail(String email);

    List<MyUserDetail> getUserByPage(Integer page, Integer limit);

    Long getUserCount();
}
