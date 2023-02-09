package com.example.BookstoreSystem.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class ValidatorConfig {

    @Bean
    public Validator Validator(){
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                // true开启快速校验，判断到有一个校验不通过就返回
                .failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}