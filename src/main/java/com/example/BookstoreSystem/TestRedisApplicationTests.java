package com.example.BookstoreSystem;

import com.example.BookstoreSystem.redis.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName TestRedisApplicationTests.java
 * @Description TODO
 * @createTime 2022年05月18日 15:07:00
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRedisApplicationTests {

    @Resource
    private CacheService cacheService;
    @Test
  public   void add() {
        cacheService.add("test1", "123f");

    }
}
