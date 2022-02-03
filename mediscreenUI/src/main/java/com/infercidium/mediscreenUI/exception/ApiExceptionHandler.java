package com.infercidium.mediscreenUI.exception;

import io.netty.resolver.dns.DnsNameResolverTimeoutException;
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
        if (e.getMessage().contains("81")) {
            LOGGER.error("MediscreenInfo: " + message + " " + e);
        }
        if (e.getMessage().contains("82")) {
            LOGGER.error("MediscreenNote: " + message + " " + e);
        }
        if (e.getMessage().contains("80")) {
            LOGGER.error("MediscreenCalcul: " + message + " " + e);
        }
        return "redirect:/error";
    }

    /**
     * Exception handling method: DnsNameResolverTimeoutException.
     * It triggers when a module is disconnect.
     * @param e represents the exception raised by the API
     *          and raised by the manager.
     * @return the error page.
     */
    @ExceptionHandler(value = {DnsNameResolverTimeoutException.class})
    public String handleDnsNameResolverTimeoutException(
            final DnsNameResolverTimeoutException e) {
        String message = "Module time out";
        LOGGER.error(message, e);
        return "redirect:/error";
    }
}
