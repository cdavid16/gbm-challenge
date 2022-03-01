package com.gbm.challenge.gbmchallenge.controller;

import com.gbm.challenge.gbmchallenge.exception.business.BusinessException;
import com.gbm.challenge.gbmchallenge.exception.business.MalformedRequestException;
import com.gbm.challenge.gbmchallenge.exception.business.UnhandledException;
import com.gbm.challenge.gbmchallenge.model.response.SendOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.NumberUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

import static com.gbm.challenge.gbmchallenge.factory.MapperResponseFactory.createNegativeResponse;

@Slf4j
@RestControllerAdvice
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public final HttpEntity<SendOrderResponse> handleBusinessErrors(final BusinessException error,
                                                                    final  WebRequest request) {
        log.error("An exception happened while processing the request, {}", error.toString());

        if (!Objects.isNull(request)) {
            return getNegativeResponse(error);
        }

        Object cash = request.getAttribute("cash", RequestAttributes.SCOPE_REQUEST);

        if (Objects.isNull(cash)) {
            return getNegativeResponse(error);
        }

        if (NumberUtils.isNumber(Objects.toString(cash))) {
            return getNegativeResponse(error);
        }

        return new HttpEntity<>(createNegativeResponse(error.getExceptionCode(),
                Objects.toString(cash)));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public final HttpEntity<SendOrderResponse> handleInputException(final IllegalArgumentException error,
                                                                    final WebRequest request) {
        log.error("An error happened while executing the request: {}", error.getStackTrace());
        error.printStackTrace();
        return handleBusinessErrors(new MalformedRequestException(), request);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR,
                    reason = "An unexpected error happened while processing the request")
    public final HttpEntity<SendOrderResponse> handleUnexpectedErrors(final Exception error,
                                                                      final WebRequest request) {
        log.error("An unhandled exception happened while processing the request {}", (Object) error.getStackTrace());
        error.printStackTrace();
        return handleBusinessErrors(new UnhandledException(), request);
    }

    private HttpEntity<SendOrderResponse> getNegativeResponse(final BusinessException error) {
        log.error("Request variable could not be set.");
        return new HttpEntity<>(createNegativeResponse(error.getExceptionCode(), "0.00"));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {

        MalformedRequestException error = new MalformedRequestException();

        return new ResponseEntity(createNegativeResponse(error.getExceptionCode(), "0.0"), headers, status);
    }
}
