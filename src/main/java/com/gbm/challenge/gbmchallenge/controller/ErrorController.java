package com.gbm.challenge.gbmchallenge.controller;

import com.gbm.challenge.gbmchallenge.exception.business.BusinessException;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public final HttpEntity<String> handleBusinessErrors(BusinessException error, WebRequest request){
        return new HttpEntity("You send a bad request, please retry.");
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,
                    reason = "An unexpected error happened while processing the request")
    public final HttpEntity<String> handleUnexpectedErrors(Exception error, WebRequest request){
        return new HttpEntity<>(String.format("An unexpected error happened while processing the request"));
    }

}
