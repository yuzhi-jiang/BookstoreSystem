package com.example.BookstoreSystem.client.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName BBook.java
 * @Description TODO
 * @createTime 2022年05月25日 10:34:00
 */
@Data
@Setter
@Getter
public class BBook implements Serializable {
    /**
     * id
     */
    private Long id;
    private Long bookid;
    /**
     * 书名字
     */
    private String bookName;
    /**
     * 作者
     */
    private String author;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * id类型
     */
    private Long typeId;
    /**
     * 库存
     */
    private Integer kucun;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 封面url
     */
    private String coverUrl;
}
