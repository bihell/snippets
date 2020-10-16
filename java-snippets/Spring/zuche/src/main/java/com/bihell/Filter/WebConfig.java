package com.bihell.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Created by toutou on 2018/10/27.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private MyTestInterceptor myTestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(myTestInterceptor).addPathPatterns("/mynearby").excludePathPatterns("/userlogin");
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration(MyTestFilter myTestFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(myTestFilter);//添加过滤器
        registration.addUrlPatterns("/show");//设置过滤路径，/*所有路径
        //registration.addInitParameter("name", "alue");//添加默认参数
        registration.setName("MyFilter");//设置优先级
        registration.setOrder(1);//设置优先级
        return registration;
    }

    /**
     * spring-boot有自己的一套web端拦截机制，若需要看到swagger发布的api文档界面，需要做一些特殊的配置，将springfox-swagger-ui包中的ui界面暴露给spring-boot资源环境。
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}