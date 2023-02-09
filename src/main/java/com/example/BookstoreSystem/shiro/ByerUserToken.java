package com.example.BookstoreSystem.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ByerUserToken extends UsernamePasswordToken {


   public ByerUserToken(String username,String password){
       super(username,password);
   }

    public ByerUserToken(String username, String password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public ByerUserToken(String requestToken) {
        this.setPassword(requestToken.toCharArray());
    }
}
