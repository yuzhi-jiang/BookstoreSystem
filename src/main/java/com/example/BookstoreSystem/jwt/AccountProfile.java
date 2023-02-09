package com.example.BookstoreSystem.jwt;

import com.example.BookstoreSystem.util.ShiroUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * 帐户资料
 * 存放账户的信息。
 * 在登录后会实例化一个此对象，然后放到subject里边。
 * 获取方法：{@link ShiroUtil#getProfile()}
 *
 * @author yefeng
 * @date 2022/05/06
 */
@Data


public class AccountProfile implements Serializable {


    /**
     * id
     */
    private String id;
    /**
     * 用户名
     */
    private String userName;
}