package com.example.demo;

import com.google.common.collect.ImmutableList;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class SwaggerConfig {
    @Value("${server.servlet.context-path:}")
    private String contextPath;
    @Value("${service.name:demo}")
    private String serviceName;

    @Bean
    public OpenAPI springShopOpenAPI() {
        OpenAPI swaggerOpenAPI = new OpenAPI()
                .info(new Info().title(serviceName.toUpperCase() + " Management APIs")
                        .description("This is an API that covers the MVP functionality")
                        .version("v0.0.1"))
                .servers(ImmutableList.of(new Server().url(contextPath)));
//        swaggerOpenAPI.components(new Components()
//                .addParameters("tenant-id", new HeaderParameter().name("tenant-id").required(true).schema(new StringSchema())));
        return swaggerOpenAPI;
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> openApi.getPaths().values().stream()
                .flatMap(pathItem -> pathItem.readOperations().stream())
//                .forEach(operation -> operation.addParametersItem(new HeaderParameter()
//                        .$ref("#/components/parameters/tenant-id")))
                ;
    }
}