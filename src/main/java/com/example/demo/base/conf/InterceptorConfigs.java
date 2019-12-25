package com.example.demo.base.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author DanierHe
 * @description
 * @date 2019-12-19-12-19 10:48
 */
@Configuration
public class InterceptorConfigs extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new ControllerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**","/templates/**","/favicon.ico");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/css")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/images/**").addResourceLocations("file:/");
        super.addResourceHandlers(registry);
    }

}
