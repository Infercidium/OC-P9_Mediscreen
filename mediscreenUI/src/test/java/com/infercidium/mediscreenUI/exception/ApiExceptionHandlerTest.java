package com.infercidium.mediscreenUI.exception;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals("redirect:/?noConnectI", result1);
        assertEquals("redirect:/?noConnectN", result2);
        assertEquals("redirect:/?noConnectC", result3);
        assertEquals("redirect:/", result4);
    }
}
