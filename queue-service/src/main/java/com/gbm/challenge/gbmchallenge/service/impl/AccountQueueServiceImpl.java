package com.gbm.challenge.gbmchallenge.service.impl;

import com.gbm.challenge.gbmchallenge.model.request.RequestDto;
import com.gbm.challenge.gbmchallenge.queue.AccountQueue;
import com.gbm.challenge.gbmchallenge.service.AccountQueueService;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class AccountQueueServiceImpl implements AccountQueueService {

    @Override
    public Queue<RequestDto> getQueue(String accountId) {
        return AccountQueue.getQueue().get(accountId);
    }

    @Override
    public boolean enqueueRequest(RequestDto requestDto) {
        return false;
    }



}
