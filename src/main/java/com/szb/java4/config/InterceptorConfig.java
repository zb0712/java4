package com.szb.java4.config;

import com.szb.java4.interceptors.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 石致彬
 * @create 2021-02-25 18:31
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/","/login","/regist","/swagger-ui.html#/","/kaptcha"
//                ,"/adminLogin");
//    }
}
