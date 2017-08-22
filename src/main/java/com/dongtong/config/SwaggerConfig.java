package com.dongtong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author auggie
 * @date 2017年5月17日
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket sampleApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(apiPaths())
                .build();
    }

	@SuppressWarnings("unchecked")
	private Predicate<String> apiPaths() {
        return Predicates.or(
                PathSelectors.regex("/sample.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MOGU API")
                .description("MOGU API")
                .version("1.0")
                .build();
    }

}
