package com.gbm.challenge.gbmchallenge.queue;

import com.gbm.challenge.gbmchallenge.model.request.RequestDto;

import java.util.HashMap;
import java.util.Queue;

public class AccountQueue extends HashMap<String, Queue<RequestDto>> {

    private static AccountQueue QUEUE = new AccountQueue();

    public static AccountQueue getQueue() {
        return QUEUE;
    }

    private AccountQueue() { }

}
