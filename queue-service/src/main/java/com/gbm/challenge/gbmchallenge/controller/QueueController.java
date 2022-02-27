package com.gbm.challenge.gbmchallenge.controller;

import com.gbm.challenge.gbmchallenge.model.request.RequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/queue")
public class QueueController {

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(code = HttpStatus.OK)
    public HttpEntity<Object> queueRequest(@RequestBody @Validated final RequestDto request){
        log.info("Request: {}", request);

        return null;
    }

}
