package com.example.BookstoreSystem.jwt;

import lombok.Data;

import javax.validation.constraints.Max;

@Data
public class JwtProperties {
    /**
     * 盐
     */
    private String secret;
    /**
     * token 过期时间，单位：秒
     */
    private long expire;
    /**
     * token刷新时间，单位：天
     */
    @Max(7)
    private Integer refreshExpire;

}