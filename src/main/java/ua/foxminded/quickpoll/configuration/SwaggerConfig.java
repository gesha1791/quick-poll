package ua.foxminded.quickpoll.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig{

    @Bean
    public Docket swaggerQuickPollApiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                       .groupName("quickpoll-api-1.0")
                       .useDefaultResponseMessages(false)
                       .select()
                       .apis(RequestHandlerSelectors.basePackage("ua.foxminded.quickpoll.v1.controller"))
                       .paths(regex("/v1/.*"))
                       .build()
                       .apiInfo(new ApiInfoBuilder()
                                        .version("1.0")
                                        .title("My REST API")
                                        .description("Some custom description of API")
                                        .contact(new Contact("John Doe", "www.example.com", "myeaddress@company.com"))
                                        .license("API TOS")
                                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                                        .build());
    }

    @Bean
    public Docket swaggerQuickPollApiV2() {
        return new Docket(DocumentationType.SWAGGER_2)
                       .groupName("quickpoll-api-2.0")
                       .useDefaultResponseMessages(false)
                       .select()
                       .apis(RequestHandlerSelectors.basePackage("ua.foxminded.quickpoll.v2.controller"))
                       .paths(regex("/v2/.*"))
                       .build()
                       .apiInfo(new ApiInfoBuilder()
                                        .version("2.0")
                                        .title("My REST API")
                                        .description("Some custom description of API")
                                        .contact(new Contact("John Doe", "www.example.com", "myeaddress@company.com"))
                                        .license("API TOS")
                                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                                        .build());
    }

}
