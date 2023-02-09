package com.example.BookstoreSystem.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class AmdminUserToken extends UsernamePasswordToken {

    public AmdminUserToken(String username, String password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public AmdminUserToken(String username, String password) {

        super(username, password);
    }
}
