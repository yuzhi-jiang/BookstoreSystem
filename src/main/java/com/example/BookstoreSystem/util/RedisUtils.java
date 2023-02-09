package com.example.BookstoreSystem.util;

import com.example.BookstoreSystem.redis.CacheService;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * redis操作工具类.</br>
 * (基于RedisTemplate)
 *
 * @author xcbeyond
 * 2018年7月19日下午2:56:24
 */

public class RedisUtils {
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");



    /**
     * 将Token放到黑名单中
     *
     * @param token Token信息
     */
    public static void addBlackList(String token) {
        CacheService.addHashCache("blackList", token, df.format(LocalDateTime.now()));
    }

    /**
     * 判断当前Token是否在黑名单中
     *
     * @param token Token信息
     */
    public static boolean isBlackList(String token) {
        if (StringUtils.isNotEmpty(token)) {
            return CacheService.hasKey("blackList", token);
        }
        return false;
    }

    /**
     * @param token 令牌
     * @return boolean
     * @throws
     * @title removeBlackList
     * @author yefeng
     * @description TODO 从黑名单中移除token
     * @updateTime 2022/05/19
     */
    public static void removeBlackList(String token){
        if (StringUtils.isNotEmpty(token)&&isBlackList(token)){
             CacheService.deleteHashCache("blackList",token,null);
        }
    }

    /**
     * 清空黑名单
     */
    public static void cleanBlackList() {
        CacheService.delete("blackList");
    }
}