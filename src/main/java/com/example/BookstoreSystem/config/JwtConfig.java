package com.example.BookstoreSystem.config;

import com.example.BookstoreSystem.jwt.JwtProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
public class JwtConfig {
    @Bean
    @ConfigurationProperties(prefix = "custom.jwt")
    public JwtProperties jwtProperties() {
        return new JwtProperties();
    }
}