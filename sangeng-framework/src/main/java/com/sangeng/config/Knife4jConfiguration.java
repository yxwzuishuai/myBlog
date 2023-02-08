package com.sangeng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Swagger2WebMvc 配置类
 * @author bing_  @create 2022/2/7-18:22
 */
@Configuration
@EnableSwagger2
public class Knife4jConfiguration {

   /* @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("社区论坛 API 列表")
                        .description("社区论坛 rest API列表")
                        .termsOfServiceUrl("https://www.jili20.com/")
                        .contact(new Contact("社区论坛","","bing_yu2001@qq.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("3.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.sangeng.controller"))
                .paths(PathSelectors.any())
                .build();
    }*/
   @Bean
   public Docket RestApi(){
       return new Docket(DocumentationType.OAS_30)
               .host("localhost:8088")
               .apiInfo(apiInfo())
               .enable(true)
               .select()
               .apis(RequestHandlerSelectors.basePackage("com.sangeng.controller"))
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
               .paths(PathSelectors.any())
               .build()
               .securitySchemes(Arrays.asList(tokenScheme()))
               .securityContexts(Arrays.asList(tokenContext()));
   }

    private HttpAuthenticationScheme tokenScheme() {
        return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name("Authorization").build();
    }

    private SecurityContext tokenContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(SecurityReference.builder()
                        .scopes(new AuthorizationScope[0])
                        .reference("Authorization")
                        .build()))
                .operationSelector(o -> o.requestMappingPattern().matches("^(?!auth).*$"))
                .build();
    }

    private List<SecurityScheme> securitySchemes () {
        List<SecurityScheme> securitySchemes = new ArrayList<>();
        securitySchemes.add(new ApiKey("Authorization", "Authorization", "header"));
        return securitySchemes;
    }

    public List<SecurityContext> securityContexts () {
        List<SecurityContext> securityContexts = new ArrayList<>();
//        securityContexts.add(SecurityContext.builder().securityReferences().forPaths(PathSelectors.regex("")))
        return securityContexts;
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("前后端分离的接口文档")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

}