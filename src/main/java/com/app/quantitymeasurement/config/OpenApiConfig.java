package com.app.quantitymeasurement.config;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/*
 * =========================================================
 * OpenAPI / Swagger Configuration
 * =========================================================
 *
 * UC17 – API Documentation Layer
 *
 * Purpose:
 * Configures OpenAPI (Swagger) documentation for the
 * Quantity Measurement REST APIs.
 *
 * Responsibilities:
 * - Provide API metadata (title, version, description)
 * - Enable Swagger UI
 * - Allow developers to explore REST endpoints
 *
 * Access URLs:
 *
 * Swagger UI:
 * http://localhost:8080/swagger-ui.html
 *
 * OpenAPI JSON:
 * http://localhost:8080/v3/api-docs
 *
 * Benefits:
 * - Interactive API testing
 * - Automatic API documentation
 * - Developer-friendly interface
 */

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI quantityMeasurementAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Quantity Measurement API")
                        .description(
                                "REST API for performing quantity measurement operations "
                                        + "such as comparison, conversion, addition, subtraction, "
                                        + "and division across different measurement types."
                        )
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Quantity Measurement Team")
                                .email("support@quantitymeasurement.com")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                        )
                );
    }
}