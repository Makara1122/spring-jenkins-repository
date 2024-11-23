package com.example.springjenkins;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// This class is used to configure the RestTemplate bean.
// The RestTemplate bean is used to make HTTP requests to other services.
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
