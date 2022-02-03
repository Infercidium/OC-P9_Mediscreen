package com.infercidium.mediscreenUI.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

public final class Filter {

    private Filter() { }

    /**
     * Instantiation of LOGGER in order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(Filter.class);

    /**
     * Filter detecting errors in the status of requests received.
     * @param response of request.
     * @return status or exception.
     */
    public static Mono<ClientResponse> infoFilter(
            final ClientResponse response) {
        HttpStatus status = response.statusCode();
        if (status.isError()) {
            LOGGER.error("mediscreenInfo: " + status);
            System.out.println(response.strategies().messageWriters());
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new Exception(body)));
        }
        return Mono.just(response);
    }

    /**
     * Filter detecting errors in the status of requests received.
     * @param response of request.
     * @return status or exception.
     */
    public static Mono<ClientResponse> noteFilter(
            final ClientResponse response) {
        HttpStatus status = response.statusCode();
        if (status.isError()) {
            LOGGER.error("mediscreenNote: " + status);
            System.out.println(response.strategies().messageWriters());
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new Exception(body)));
        }
        return Mono.just(response);
    }

    /**
     * Filter detecting errors in the status of requests received.
     * @param response of request.
     * @return status or exception.
     */
    public static Mono<ClientResponse> assessFilter(
            final ClientResponse response) {
        HttpStatus status = response.statusCode();
        if (status.isError()) {
            LOGGER.error("mediscreenCalcul: " + status);
            System.out.println(response.strategies().messageWriters());
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new Exception(body)));
        }
        return Mono.just(response);
    }
}
