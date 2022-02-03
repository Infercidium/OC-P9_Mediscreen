package com.infercidium.mediscreenUI.exception;

import io.netty.handler.codec.dns.DefaultDnsQuestion;
import io.netty.handler.codec.dns.DnsQuestion;
import io.netty.handler.codec.dns.DnsRecordType;
import io.netty.resolver.dns.DnsNameResolverTimeoutException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.ConnectException;
import java.net.InetSocketAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {ApiExceptionHandler.class})
@RunWith(SpringRunner.class)
class ApiExceptionHandlerTest {

    @MockBean
    private ConnectException connectException;

    @Autowired
    private ApiExceptionHandler apiExceptionHandler;

    @Test
    void handleConnectException() {
        Mockito.when(connectException.getMessage()).thenReturn("81");
        String result1 = apiExceptionHandler.handleConnectException(connectException);

        Mockito.when(connectException.getMessage()).thenReturn("82");
        String result2 = apiExceptionHandler.handleConnectException(connectException);

        Mockito.when(connectException.getMessage()).thenReturn("80");
        String result3 = apiExceptionHandler.handleConnectException(connectException);

        Mockito.when(connectException.getMessage()).thenReturn("Test");
        String result4 = apiExceptionHandler.handleConnectException(connectException);

        assertEquals("redirect:/error", result1);
        assertEquals("redirect:/error", result2);
        assertEquals("redirect:/error", result3);
        assertEquals("redirect:/error", result4);
    }

    @Test
    void handleDnsNameResolverTimeoutException() {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(1);
        DnsQuestion dnsQuestion = new DefaultDnsQuestion("test", new DnsRecordType(1, "test"));
        String result = apiExceptionHandler.handleDnsNameResolverTimeoutException(new DnsNameResolverTimeoutException(inetSocketAddress, dnsQuestion, "test"));
        assertEquals("redirect:/error", result);
    }
}
