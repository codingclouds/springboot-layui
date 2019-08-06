package com.haiyu.manager.config;

import com.haiyu.manager.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ Author     : wzt.
 * @ Date       : Created in 12:41 2019/8/5
 * @ Description: 拦截器配置类：指定拦截器
 * @ Modified By:
 * @ Version    : 1.0$
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //指定过滤器AuthInterceptor对所有url进行拦截
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
