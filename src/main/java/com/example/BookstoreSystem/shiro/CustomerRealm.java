package com.example.BookstoreSystem.shiro;


import com.example.BookstoreSystem.client.bean.BRole;
import com.example.BookstoreSystem.client.bean.MyUserDetail;
import com.example.BookstoreSystem.client.entity.Permission;
import com.example.BookstoreSystem.client.service.IUserService;
import com.example.BookstoreSystem.util.JwtUtil1;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
 
@Slf4j
public class CustomerRealm extends AuthorizingRealm {
 
    @Autowired(required = false)
    IUserService iUserService;
 
    // 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }
 
    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ByerUserToken;

    }
 
 
    /**
     * 授权的方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("开始授权！");
        if (iUserService == null) {
            return null;
        }

        //获取用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        MyUserDetail user = iUserService.getUserDetail(userName);


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
     * 认证的方法
     * @param authenticationToken
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        log.debug("开始认证！");
        if (iUserService == null) {
            log.warn("IUserService Is Empty");
            return null;
        }
        ByerUserToken jwtToken = (ByerUserToken) authenticationToken;
        String token = jwtToken.getCredentials().toString();//password
        String username = JwtUtil1.getUsername(token);
        //toke过期
        if (JwtUtil1.isExpire(token)) {
            throw new ExpiredCredentialsException();
        }
 
        //用户不存在（这个在登录时不会进入，只有在token校验时才有可能进入）
        MyUserDetail user = iUserService.getUserDetail(username);
        if (username == null || user == null)
            throw new UnknownAccountException();
 
        //密码错误(这里获取到password，就是3件套处理后的保存到数据库中的凭证，作为密钥)
        if (!JwtUtil1.verifyToken(token, username, user.getPassword())) {
            throw new IncorrectCredentialsException();
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                username, token, this.getName());
        return simpleAuthenticationInfo;
    }
 
    /**
     * 这里可以自定义密码比较器
     *
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }
}