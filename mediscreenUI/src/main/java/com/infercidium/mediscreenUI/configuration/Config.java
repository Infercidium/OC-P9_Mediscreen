package com.infercidium.mediscreenUI.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class Config {
    /**
     * Instantiates the mediscreen-info API url.
     */
    @Value("${info.url}")
    private String infoUrlBase;

    /**
     * Instantiates the WebClient with the url of mediscreen-info.
     * @return the WebClient usable by the proxy.
     */
    @Bean
    public WebClient infoClient() {
        return WebClient.create(infoUrlBase);
    }

    /**
     * Instantiates the mediscreen-note API url.
     */
    @Value("${note.url}")
    private String noteUrlBase;

    /**
     * Instantiates the WebClient with the url of mediscreen-note.
     * @return the WebClient usable by the proxy.
     */
    @Bean
    public WebClient noteClient() {
        return WebClient.create(noteUrlBase);
    }
}
