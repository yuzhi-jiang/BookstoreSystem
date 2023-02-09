package com.example.BookstoreSystem.shiro;

import com.example.BookstoreSystem.jwt.JWTFilter;
import com.example.BookstoreSystem.jwt.JWTRealm;
import com.example.BookstoreSystem.jwt.WhiteList;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShiroConfig1 {

    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(JWTRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 使用自己的realm
        manager.setRealm(realm);


        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);//关闭session后，不能获取到session subject获取不到用户信息
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);

        return manager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);


        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/client/login");
//        factoryBean.setUnauthorizedUrl("/401");

        /*
         * 自定义url规则
         * http://shiro.apache.org/web.html#urls-
         */
        Map<String, String> filterRuleMap = new HashMap<>();


//        // 访问401和404页面不通过我们的Filter
//        filterRuleMap.put("/", "anon");
//        filterRuleMap.put("/401", "anon");
//        filterRuleMap.put("/404", "anon");
//        filterRuleMap.put("/css/**", "anon");
//        filterRuleMap.put("/js/**", "anon");
//        filterRuleMap.put("/img/**", "anon");
//        filterRuleMap.put("/login", "anon");
//        filterRuleMap.put("/rbacManager/401/*", "anon");
//        filterRuleMap.put("/client/login", "anon");
//        filterRuleMap.put("/client/register", "anon");
//        filterRuleMap.put("/client/forget", "anon");
//        filterRuleMap.put("/register", "anon");
//        filterRuleMap.put("/userLogin", "anon");
//        filterRuleMap.put("/registerUser", "anon");


        WhiteList.ALL.forEach(str -> {
            filterRuleMap.put(str, "anon");
        });

        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**", "jwtAuthc");
        factoryBean.setUnauthorizedUrl("/401");

        Map<String, Filter> customisedFilters = new LinkedHashMap<>();
        customisedFilters.put("jwtAuthc", new JWTFilter());
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        factoryBean.setFilters(customisedFilters);
        return factoryBean;
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}