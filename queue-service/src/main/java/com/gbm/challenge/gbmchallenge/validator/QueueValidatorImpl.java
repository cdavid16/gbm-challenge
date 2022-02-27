package com.gbm.challenge.gbmchallenge.validator;

import com.gbm.challenge.gbmchallenge.model.RequestDto;
import com.gbm.challenge.gbmchallenge.queue.AccountQueue;

import java.util.LinkedList;
import java.util.Queue;


public class QueueValidatorImpl implements QueueValidator {

    @Override
    public boolean validate(String accountId, RequestDto requestDto) {
        Queue<RequestDto> queue = getQueue(accountId);

        if (queue.isEmpty()) {
            return true;
        }

        Long timestamp = null; //TODO get timestamp;
        while(isNotInRange(timestamp, queue)) {
            queue.poll();
        }
        return true;
    }

    private Queue<RequestDto> getQueue(String accountId) {
        AccountQueue.getQueue().putIfAbsent(accountId, new LinkedList<>());
        return AccountQueue.getQueue().get(accountId);
    }

    private boolean isNotInRange(Long timestamp, Queue<RequestDto> transactionsQueue) {
        if(!transactionsQueue.isEmpty()) {
            return false;
        }

        return true;
    }

}
