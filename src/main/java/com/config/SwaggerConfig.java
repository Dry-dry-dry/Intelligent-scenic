package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * @author zhangyao
 * createTime 2019-7-22
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    /**
     * 实现基本的跨域请求
     * @author linhongcun
     *
     */

        @Bean
        public CorsFilter corsFilter() {
            final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
            final CorsConfiguration corsConfiguration = new CorsConfiguration();
            /*是否允许请求带有验证信息*/
            corsConfiguration.setAllowCredentials(true);
            /*允许访问的客户端域名*/
            corsConfiguration.addAllowedOrigin("*");
            /*允许服务端访问的客户端请求头*/
            corsConfiguration.addAllowedHeader("*");
            /*允许访问的方法名,GET POST等*/
            corsConfiguration.addAllowedMethod("*");
            urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
            return new CorsFilter(urlBasedCorsConfigurationSource);
        }



    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("智慧景区")
                .description("智慧景区管理")
                .version("1.0")
                .build();
    }
}
