package com.example.BookstoreSystem.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wxl
 * @date 2021-08-15 18:44
 */
@Slf4j
@Component
public class CacheService {
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    private static final String DEFAULT_KEY_PREFIX = "";
    private final int EXPIRE_TIME = 1;
    private final TimeUnit EXPIRE_TIME_TYPE = TimeUnit.DAYS;

    private static CacheService cacheService;
    /**
     * 初始化
     */
    @PostConstruct
    public  void init() {
        cacheService = this;
        cacheService.redisTemplate = this.redisTemplate;
    }
    /**
     * 数据缓存至redis
     *
     * @param key
     * @param value
     * @return
     */
    public static   <K, V> void add(K key, V value) {
        try {
            if (value != null) {
                cacheService.redisTemplate
                        .opsForValue()
                        .set(DEFAULT_KEY_PREFIX + key, JSON.toJSONString(value));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("数据缓存至redis失败");
        }
    }

    /**
     * 数据缓存至redis并设置过期时间
     *
     * @param key
     * @param value
     * @return
     */
    public static  <K, V> void add(K key, V value, long timeout, TimeUnit unit) {
        try {
            if (value != null) {
                cacheService.redisTemplate
                        .opsForValue()
                        .set(DEFAULT_KEY_PREFIX + key, JSON.toJSONString(value), timeout, unit);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("数据缓存至redis失败");
        }
    }

    /**
     * 写入 hash-set,已经是key-value的键值，不能再写入为hash-set
     *
     * @param key    must not be {@literal null}.
     * @param subKey must not be {@literal null}.
     * @param value  写入的值
     */
    public static <K, SK, V> void addHashCache(K key, SK subKey, V value) {
        cacheService.redisTemplate.opsForHash().put(DEFAULT_KEY_PREFIX + key, subKey, value);
    }

    public static <K,Sk,V> void deleteHashCache(K key,Sk subKey,V value){
        cacheService.redisTemplate.opsForHash().delete(DEFAULT_KEY_PREFIX+key,subKey);
    }

    /**
     * 写入 hash-set,并设置过期时间
     *
     * @param key    must not be {@literal null}.
     * @param subKey must not be {@literal null}.
     * @param value  写入的值  TimeUnit是时间单位
     */
    public static <K, SK, V> void addHashCache(K key, SK subKey, V value, long timeout, TimeUnit unit) {
        cacheService.redisTemplate.opsForHash().put(DEFAULT_KEY_PREFIX + key, subKey, value);
//        cacheService.redisTemplate.expire(DEFAULT_KEY_PREFIX + key, timeout, unit);
        cacheService.redisTemplate.expire(DEFAULT_KEY_PREFIX + key, timeout, unit);
    }

    /**
     * 获取 hash-setvalue
     *
     * @param key    must not be {@literal null}.
     * @param subKey must not be {@literal null}.
     */
    public static <K, SK> Object getHashCache(K key, SK subKey) {
        return  cacheService.redisTemplate.opsForHash().get(DEFAULT_KEY_PREFIX + key, subKey);
    }
    /**
     * 判断key和hasKey下是否有值
     *
     * @param key
     * @param subKey
     */
    public static Boolean hasKey(String key, String subKey) {
        return cacheService.redisTemplate.opsForHash().hasKey(key, subKey);
    }

    /**
     * 从redis中获取缓存数据，转成对象
     *
     * @param key   must not be {@literal null}.
     * @param clazz 对象类型
     * @return
     */
    public <K, V> V getObject(K key, Class<V> clazz) {
        String value = this.get(key);
        V result = null;
        if (!StringUtils.isEmpty(value)) {
            result = JSONObject.parseObject(value, clazz);
        }
        return result;
    }

    /**
     * 从redis中获取缓存数据，转成list
     *
     * @param key   must not be {@literal null}.
     * @param clazz 对象类型
     * @return
     */
    public <K, V> List<V> getList(K key, Class<V> clazz) {
        String value = this.get(key);
        List<V> result = Collections.emptyList();
        if (!StringUtils.isEmpty(value)) {
            result = JSONArray.parseArray(value, clazz);
        }
        return result;
    }

    /**
     * 功能描述：Get the value of {@code key}.
     *
     * @param key must not be {@literal null}.
     * @return java.lang.String
     * @date 2021/9/19
     **/
    public static <K> String get(K key) {
        String value;
        try {
            value = cacheService.redisTemplate.opsForValue().get(DEFAULT_KEY_PREFIX + key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("从redis缓存中获取缓存数据失败");
        }
        return value;
    }

    /**
     * 删除key
     */
    public static void delete(String key) {
        cacheService.redisTemplate.delete(key);
    }

    /**
     * 批量删除key
     */
    public static void delete(Collection<String> keys) {
        cacheService.redisTemplate.delete(keys);
    }

    /**
     * 序列化key
     */
    public static  byte[] dump(String key) {
        return cacheService.redisTemplate.dump(key);
    }

    /**
     * 是否存在key
     */
    public static  Boolean hasKey(String key) {
        return cacheService.redisTemplate.hasKey(key);
    }

    /**
     * 设置过期时间
     */
    public static  Boolean expire(String key, long timeout, TimeUnit unit) {
        return cacheService.redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 设置过期时间
     */
    public static  Boolean expireAt(String key, Date date) {
        return cacheService.redisTemplate.expireAt(key, date);
    }


    /**
     * 移除 key 的过期时间，key 将持久保持
     */
    public static  Boolean persist(String key) {
        return cacheService.redisTemplate.persist(key);
    }

    /**
     * 返回 key 的剩余的过期时间
     */
    public static  Long getExpire(String key, TimeUnit unit) {
        return cacheService.redisTemplate.getExpire(key, unit);
    }

    /**
     * 返回 key 的剩余的过期时间
     */
    public static  Long getExpire(String key) {
        return cacheService.redisTemplate.getExpire(key);
    }
}

