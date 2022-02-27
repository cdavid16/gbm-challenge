package com.gbm.challenge.gbmchallenge.service;


import com.gbm.challenge.gbmchallenge.model.request.RequestDto;

import java.util.Queue;

public interface AccountQueueService {

    Queue<RequestDto> getQueue(String accountId);

    boolean enqueueRequest(RequestDto requestDto);
}
