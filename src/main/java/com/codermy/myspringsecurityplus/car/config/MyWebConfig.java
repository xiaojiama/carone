package com.codermy.myspringsecurityplus.car.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.net.URL;


@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @SneakyThrows
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //当前项目下路径
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath().replaceAll("\\\\", "/");
        String s = "file:"+courseFile+"/src/main/resources/static/upload/file/";
        //registry.addResourceHandler("/image/**").addResourceLocations("file:C:/Users/maxiaojia/IdeaProjects/carone/src/main/resources/static/upload/imgs/");
        registry.addResourceHandler("/image/**").addResourceLocations(s);
    }

}
