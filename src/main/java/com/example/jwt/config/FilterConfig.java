package com.example.jwt.config;

import com.example.jwt.filter.FilterTest1;
import com.example.jwt.filter.FilterTest2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<FilterTest1> filter1(){
        FilterRegistrationBean<FilterTest1> bean=new FilterRegistrationBean<>(new FilterTest1());
        bean.addUrlPatterns("/*");
        bean.setOrder(1); // 낮은 번호가 필터중에 가장 먼저 실행됨
        return bean;
    }
    @Bean
    public FilterRegistrationBean<FilterTest2> filter2(){
        FilterRegistrationBean<FilterTest2> bean=new FilterRegistrationBean<>(new FilterTest2());
        bean.addUrlPatterns("/*");
        bean.setOrder(0); // 낮은 번호가 필터중에 가장 먼저 실행됨
        return bean;
    }
}
