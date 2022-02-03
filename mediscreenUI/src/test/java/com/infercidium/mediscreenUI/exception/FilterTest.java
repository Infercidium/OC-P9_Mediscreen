package com.infercidium.mediscreenUI.exception;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {Filter.class})
@RunWith(SpringRunner.class)
class FilterTest {

    private ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder().build();
    private ClientResponse clientOkResponse = ClientResponse.create(HttpStatus.OK, exchangeStrategies).build();
    private ClientResponse clientBadResponse = ClientResponse.create(HttpStatus.BAD_REQUEST, exchangeStrategies).build();
    private ClientResponse clientServerResponse = ClientResponse.create(HttpStatus.INTERNAL_SERVER_ERROR, exchangeStrategies).build();

    @Test
    void infoFilter() {
        Mono<ClientResponse> result1 = Filter.infoFilter(clientOkResponse);
        assertEquals("MonoJust", result1.toString());

        Mono<ClientResponse> result2 = Filter.infoFilter(clientBadResponse);
        assertEquals("MonoFlatMap", result2.toString());

        Mono<ClientResponse> result3 = Filter.infoFilter(clientServerResponse);
        assertEquals("MonoFlatMap", result3.toString());

    }

    @Test
    void noteFilter() {
        Mono<ClientResponse> result1 = Filter.noteFilter(clientOkResponse);
        assertEquals("MonoJust", result1.toString());

        Mono<ClientResponse> result2 = Filter.noteFilter(clientBadResponse);
        assertEquals("MonoFlatMap", result2.toString());

        Mono<ClientResponse> result3 = Filter.noteFilter(clientServerResponse);
        assertEquals("MonoFlatMap", result3.toString());
    }

    @Test
    void assessFilter() {
        Mono<ClientResponse> result1 = Filter.assessFilter(clientOkResponse);
        assertEquals("MonoJust", result1.toString());

        Mono<ClientResponse> result2 = Filter.assessFilter(clientBadResponse);
        assertEquals("MonoFlatMap", result2.toString());

        Mono<ClientResponse> result3 = Filter.assessFilter(clientServerResponse);
        assertEquals("MonoFlatMap", result3.toString());
    }
}