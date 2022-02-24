package com.gbm.challenge.gbmchallenge.utils;

import com.gbm.challenge.gbmchallenge.enums.TransactionEnum;
import com.gbm.challenge.gbmchallenge.exception.business.InvalidOperationException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransactionHelper {

    public static boolean isValid(String transaction) {
        try{
            parseStringToEnum(transaction);
        } catch (InvalidOperationException e){
            return false;
        }
        return true;
    }

    public static TransactionEnum parseStringToEnum(String transaction) {
        final TransactionEnum transactionEnum;
        try{
            transactionEnum = TransactionEnum.valueOf(transaction);
        } catch (IllegalArgumentException e) {
            throw new InvalidOperationException();
        }
        return transactionEnum;
    }

}
