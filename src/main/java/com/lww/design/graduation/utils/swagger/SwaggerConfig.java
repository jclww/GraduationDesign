package com.lww.design.graduation.utils.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Profile("dev")//仅在开发环境开放在线文档
@Configuration
@EnableWebMvc //NOTE: Only needed in a non-springboot application
@EnableSwagger2
@ComponentScan("com.lww.design.graduation.controller")
public class SwaggerConfig {
    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("SpringMVCSeedProject API")
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringMVCSeedProject 在线API")
                .description("SpringMVCSeedProject 在线API ")
                .termsOfServiceUrl("")
                .licenseUrl("")
                .version("1.0")
                .build();
    }
}
