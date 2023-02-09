package com.example.BookstoreSystem.jwt;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.BookstoreSystem.client.bean.BRole;
import com.example.BookstoreSystem.client.bean.MyUserDetail;
import com.example.BookstoreSystem.client.entity.Permission;
import com.example.BookstoreSystem.client.service.impl.UserService;
import com.example.BookstoreSystem.util.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName JWTRealm.java
 * @Description TODO
 * @createTime 2022年05月04日 18:29:00
 */
public class JWTRealm extends AuthorizingRealm {


    private static final Log log = LogFactory.get();

    @Resource
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }




    @Override
    protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
      return   super.getAuthorizationInfo(principals);
    }


    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("doGetAuthorizationInfo 进行授权，检查权限");

        AccountProfile accountProfile = (AccountProfile) principals.getPrimaryPrincipal();
        String username = accountProfile.getUserName();
        log.info("授权时 token:{}", accountProfile);
        log.info("授权时 username:{}", username);
        MyUserDetail user = userService.getUserDetail(username);
        log.info("授权时 user:{}", user);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //进行授权角色
        for (BRole role : user.getRoles()) {
            //添加角色
            authorizationInfo.addRole(role.getName());
//            //添加权限
//            for (Permission permission : role.getPermissions()) {
//                authorizationInfo.addStringPermission(permission.getName());
//            }
        }
        //添加权限
        for (Permission permission : user.getPermissions()) {
            authorizationInfo.addStringPermission(permission.getName());
        }
        log.info(String.valueOf(authorizationInfo.getRoles()));
        log.info(String.valueOf(authorizationInfo.getStringPermissions()));
        return authorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        JWTToken jwtToken = (JWTToken) auth;
        String token = jwtToken.getPrincipal().toString();
        log.info("login token:" + token);
        //解析token
        if (token == null || token.isBlank()) {
            throw new AuthenticationException("token为空!");
        }
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUserName(token);
        if (username == null) {
            throw new UnknownAccountException("token为空，请重新登录");
        }
        MyUserDetail userBean = userService.getUserDetail(username);
        AccountProfile accountProfile = new AccountProfile();
        accountProfile.setId(String.valueOf(userBean.getId()));
        accountProfile.setUserName(userBean.getUsername());
        log.info("doGetAuthenticationInfo 进行认证，检查用户名密码,返回认证信息,userBean:{},accountProfile:{}", userBean, accountProfile);
        return new SimpleAuthenticationInfo(accountProfile, jwtToken.getCredentials(), getName());
    }
}