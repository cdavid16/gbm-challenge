package com.gbm.challenge.gbmchallenge.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthCheckController {


    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<String> ping(){
        log.info("Ping succeeded.");
        return new HttpEntity<>("I am alive");
    }

}
