package com.ftx.saysomthing.config;

/**
 * @author FanJiangFeng
 * @version 1.0.0
 * @ClassName WebConfig.java
 * @Description TODO
 * @createTime 2020年06月22日 15:53:00
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor ;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }
}