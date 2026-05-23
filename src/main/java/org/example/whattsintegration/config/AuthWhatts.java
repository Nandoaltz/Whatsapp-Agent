package org.example.whattsintegration.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthWhatts implements RequestInterceptor {

    @Value("${api.key}")
    String apiKey;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("apikey",apiKey);
        requestTemplate.header("Content-Type","application/json");
    }
}
