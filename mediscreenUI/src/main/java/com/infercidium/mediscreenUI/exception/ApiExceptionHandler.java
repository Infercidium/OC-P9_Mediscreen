package com.infercidium.mediscreenUI.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;

@ControllerAdvice
public class ApiExceptionHandler {
    /**
     * Instantiation of LOGGER in order to inform in console.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ApiExceptionHandler.class);

    /**
     * Exception handling method: ConnectException.
     * It triggers when a module is disconnect.
     * @param e represents the exception raised by the API
     *          and raised by the manager.
     * @return the error page.
     */
    @ExceptionHandler(value = {ConnectException.class})
    public String handleConnectException(
            final ConnectException e) {
        String message = "Connection not found";
        LOGGER.error(message, e);
        if (e.getMessage().contains("81")) {
            return "redirect:/?noConnectI";
        }
        if (e.getMessage().contains("82")) {
            return "redirect:/?noConnectN";
        }
        if (e.getMessage().contains("80")) {
            return "redirect:/?noConnectC";
        }
        return "redirect:/";
    }
}
