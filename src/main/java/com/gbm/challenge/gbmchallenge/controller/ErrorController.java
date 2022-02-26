package com.gbm.challenge.gbmchallenge.controller;

import com.gbm.challenge.gbmchallenge.exception.business.BusinessException;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.RequestContext;

import java.util.Objects;

import static com.gbm.challenge.gbmchallenge.factory.MapperResponseFactory.createNegativeResponse;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public final HttpEntity<String> handleBusinessErrors(BusinessException error, WebRequest request){
        log.error("An exception happened while processing the request, {}", error.toString());
        try{
            Object cash = request.getAttribute("cash", RequestAttributes.SCOPE_REQUEST);

            if (Objects.isNull(cash)) {
                throw new IllegalArgumentException("Request variable could not be set.");
            }

            return new HttpEntity<>(createNegativeResponse(error.getExceptionCode(),
                    Objects.toString(cash)).toString());
        } catch (Exception e) {
            return handleUnexpectedErrors(e, request);
        }
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,
                    reason = "An unexpected error happened while processing the request")
    public final HttpEntity<String> handleUnexpectedErrors(Exception error, WebRequest request){
        log.error("An unhandled exception happened while processing the request {}", (Object) error.getStackTrace());
        return new HttpEntity<>("An unexpected error happened while processing the request");
    }

}
