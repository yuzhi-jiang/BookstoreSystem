package com.example.BookstoreSystem.util;

import com.example.BookstoreSystem.shiro.AuthConstant;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName TokenUtil.java
 * @Description TODO
 * @createTime 2022年05月11日 16:19:00
 */
public class TokenUtil {
    /**
     * 令牌头是否来自于请求头
     */
    private static final boolean TOKEN_FROM_HEADER = false;
    public static String getToken(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("Authentication");
//        System.out.println("header中的token为" + token);
        LogUtil.info("header中的token为" + token);
        // 自定义Header也可以，但浏览器不会存自定义的Header，需要前端自己去存
        Cookie[] cookies = request.getCookies();
        if (token==null|| token.equals("null") ||!StringUtils.hasText(token)) {// 如果没有token，则从cookie中获取
            token = CookieUtil.getAuthenticationByCookie(cookies);
            LogUtil.info("cookies中的token为" + token);
        }
        LogUtil.info("token为{}" ,token);
        return token;
    }

    public static String getFlushToken(HttpServletRequest request){
        return request.getHeader("flushToken");
    }
    public static void setToken(ServletResponse servletResponse, String token) {
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        if (TOKEN_FROM_HEADER) {
            LogUtil.info("将token写入header");
            response.setHeader("Authentication", token);
        }else{
            LogUtil.info("将token写入cookie");
           response.setHeader(AuthConstant.AUTHENTICATION_HEADER_SET_COOKIE, "Authentication="+token);
        }
    }
    public static String getUserId(HttpServletRequest request){
        String token = getToken(request);
        return JwtUtil.getUserIdByToken(token);
    }
}
