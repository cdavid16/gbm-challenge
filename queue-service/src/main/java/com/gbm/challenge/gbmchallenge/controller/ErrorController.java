package com.gbm.challenge.gbmchallenge.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,
                    reason = "An unexpected error happened while processing the request")
    public final HttpEntity<String> handleUnexpectedErrors(Exception error, WebRequest request){
        log.error("An unhandled exception happened while processing the request {}", (Object) error.getStackTrace());
        return new HttpEntity<>("An unexpected error happened while processing the request");
    }

}
