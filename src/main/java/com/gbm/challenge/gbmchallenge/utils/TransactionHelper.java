package com.gbm.challenge.gbmchallenge.utils;

import com.gbm.challenge.gbmchallenge.enums.TransactionEnum;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransactionHelper {

    public static boolean isValid(String transaction) {
        try{
            TransactionEnum.valueOf(transaction);
        } catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }

}
