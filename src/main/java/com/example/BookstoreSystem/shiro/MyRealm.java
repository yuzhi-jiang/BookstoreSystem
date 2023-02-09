package com.example.BookstoreSystem.shiro;

import com.example.BookstoreSystem.client.bean.BRole;
import com.example.BookstoreSystem.client.bean.MyUserDetail;
import com.example.BookstoreSystem.client.entity.Permission;
import com.example.BookstoreSystem.client.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private IUserService loginService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ByerUserToken || token instanceof AmdminUserToken;
    }


    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        MyUserDetail user = loginService.getUserDetail(userName);


        System.out.println(user.getUsername());
        //进行授权角色
        for (BRole role : user.getRoles()) {
            //添加角色
            System.out.println(role.getName());
            authorizationInfo.addRole(role.getName());
            //添加权限
            for (Permission permission : role.getPermissions()) {
                authorizationInfo.addStringPermission(permission.getName());
            }
        }
//        authorizationInfo.setRoles(userService.getRoles(userName));
//        //进行授权权限
//        authorizationInfo.setStringPermissions(userService.getPermissions(userName));
        return authorizationInfo;
    }

    /**
     * 执行认证逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken instanceof ByerUserToken) {
            ByerUserToken token = (ByerUserToken) authenticationToken;
            String username = token.getUsername();
//select * from user
            MyUserDetail user = loginService.getUserDetail(username);
            if (user == null) return null;

            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        } else if (authenticationToken instanceof AmdminUserToken) {

        }
        return null;
    }
}
