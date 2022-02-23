package com.gbm.challenge.gbmchallenge.wrapper;

import java.util.UUID;

@FunctionalInterface
public interface UUIDSource {

    UUID getRandomUUID();

    static UUIDSource generateRandom() {
        return UUID::randomUUID;
    }

}
