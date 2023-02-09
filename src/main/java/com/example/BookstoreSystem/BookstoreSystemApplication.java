package com.example.BookstoreSystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @readme 本项目的所有接口采用json 协议，为平台提供后台接口。
 */
@MapperScan({"com.example.BookstoreSystem.client.mapper"})
//@MapperScan({"com.example.BookstoreSystem.client.mapper.mappers"})
@SpringBootApplication(scanBasePackages = "com.example.BookstoreSystem.*")
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
public class BookstoreSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookstoreSystemApplication.class, args);
    }

}
