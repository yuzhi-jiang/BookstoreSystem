package com.example.BookstoreSystem.bean;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName testEnum.java
 * @Description TODO
 * @createTime 2022年04月18日 10:12:00
 */
public enum testEnum {
    ;
    private String book;

    testEnum(String book) {
        this.book = book;
    }

    public void test(){
        System.out.println("test");
    }
}
