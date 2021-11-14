package com.storagebusiness.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {

    @Value("${resource.core.url}")
    private String coreResourceUrl;

    @Bean
    public WebClient webClientBean() {
        return WebClient.builder().baseUrl(coreResourceUrl).build();
    }
}
