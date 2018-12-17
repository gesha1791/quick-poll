package ua.foxminded.quickpoll.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                       .apiInfo(apiInfo())
                       .useDefaultResponseMessages(false)
                       .select()
                       .apis(RequestHandlerSelectors.basePackage("ua.foxminded.quickpoll.controller"))
                       .paths(PathSelectors.any())
                       .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                       .title("My REST API")
                       .description("Some custom description of API")
                       .contact(new Contact("John Doe", "www.example.com", "myeaddress@company.com"))
                       .license("API TOS")
                       .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                       .version("1.0.0")
                       .build();
    }
}
