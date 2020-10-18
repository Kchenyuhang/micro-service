package com.soft1851.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/5
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "share-app Swagger文档",
                "github地址：https://github.com/Kchenyuhang/micro-service",
                "API V1.0",
                "Terms of service",
                new Contact("陈宇航", "https://yhChen-99.cn", "kchenyuhang@gmail.com"),
                "Apache", "http://www.apache.org/", Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.soft1851.user"))
                .build()
                .apiInfo(apiInfo());
    }
}
