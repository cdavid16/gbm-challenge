package com.gbm.challenge.gbmchallenge.controller;

import com.gbm.challenge.gbmchallenge.exception.business.ClosedMarketException;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {


    @GetMapping("/ping")
    public HttpEntity<String> ping(){
        throw new ClosedMarketException();
    }

}
