package com.infercidium.mediscreenUI.configuration;

import com.infercidium.mediscreenUI.exception.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class Config {
    /**
     * Instantiates the mediscreen-info API url.
     */
    @Value("${info.url}")
    private String infoUrlBase;

    /**
     * Instantiates filter.
     */
    private final ExchangeFilterFunction filterInfoFunction
            = ExchangeFilterFunction.ofResponseProcessor(Filter::infoFilter);

    /**
     * Instantiates the WebClient with the url of mediscreen-info.
     * @return the WebClient usable by the proxy.
     */
    @Bean
    public WebClient infoClient() {
        return WebClient.builder().filter(filterInfoFunction)
                .baseUrl(infoUrlBase).build();
    }

    /**
     * Instantiates the mediscreen-note API url.
     */
    @Value("${note.url}")
    private String noteUrlBase;

    /**
     * Instantiates filter.
     */
    private final ExchangeFilterFunction filterNoteFunction
            = ExchangeFilterFunction.ofResponseProcessor(Filter::noteFilter);

    /**
     * Instantiates the WebClient with the url of mediscreen-note.
     * @return the WebClient usable by the proxy.
     */
    @Bean
    public WebClient noteClient() {
        return WebClient.builder().filter(filterNoteFunction)
                .baseUrl(noteUrlBase).build();
    }

    /**
     * Instantiates the mediscreen-Calcul API url.
     */
    @Value("${assess.url}")
    private String assessUrlBase;

    /**
     * Instantiates filter.
     */
    private final ExchangeFilterFunction filterAssessFunction
            = ExchangeFilterFunction.ofResponseProcessor(Filter::assessFilter);

    /**
     * Instantiates the WebClient with the url of mediscreen-calcul.
     * @return the WebClient usable by the proxy.
     */
    @Bean
    public WebClient assessClient() {
        return WebClient.builder().filter(filterAssessFunction)
                .baseUrl(assessUrlBase).build();
    }
}
