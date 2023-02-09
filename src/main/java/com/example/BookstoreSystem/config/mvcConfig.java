package com.example.BookstoreSystem.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class mvcConfig implements WebMvcConfigurer {


    //跨域支持
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600);
    }
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setUrlDecode(false);
        configurer.setUrlPathHelper(urlPathHelper);
        // 设置是否模糊匹配，默认真。例如/user是否匹配/user.*。如果真，也就是说"/user.html"的请求会被"/user"的Controller所拦截。
//        configurer.setUseSuffixPatternMatch(false);
//        // 设置是否自动后缀模式匹配，默认真。如/user是否匹配/user/。如果真，也就是说, "/user"和"/user/"都会匹配到"/user"的Controller。
//        configurer.setUseTrailingSlashMatch(true);
//        WebMvcConfigurer.super.configurePathMatch(configurer);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/kickout").setViewName("kickout");
        registry.addViewController("/client/login").setViewName("client/login");
        registry.addViewController("/client/register").setViewName("client/register");
        registry.addViewController("/client/forget").setViewName("client/forget");
        registry.addViewController("/client/phone_login").setViewName("client/phone_login");
        registry.addViewController("/client/Reception_index").setViewName("client/Reception_index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index2").setViewName("index2");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
    }
}
