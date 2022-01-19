package com.infercidium.mediscreenUI.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {
    @Value("${info.url}")
    public String infoUrlBase;
    @Bean
    public WebClient infoClient() {
        return WebClient.create(infoUrlBase);
    }
}
