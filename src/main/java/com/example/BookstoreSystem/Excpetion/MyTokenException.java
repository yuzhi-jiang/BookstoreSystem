package com.example.BookstoreSystem.Excpetion;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName MyTokenException.java
 * @Description TODO
 * @createTime 2022年05月11日 14:56:00
 */
public class MyTokenException extends RuntimeException {

    public MyTokenException(String message) {
        super( message);
    }
}
