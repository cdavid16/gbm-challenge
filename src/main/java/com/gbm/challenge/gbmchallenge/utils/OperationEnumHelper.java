package com.gbm.challenge.gbmchallenge.utils;

import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import com.gbm.challenge.gbmchallenge.exception.business.InvalidOperationException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OperationEnumHelper {

    public static boolean isValid(String transaction) {
        try{
            parseStringToEnum(transaction);
        } catch (InvalidOperationException e){
            return false;
        }
        return true;
    }

    public static OperationEnum parseStringToEnum(String transaction) {
        final OperationEnum operationEnum;
        try{
            operationEnum = OperationEnum.valueOf(transaction);
        } catch (IllegalArgumentException e) {
            throw new InvalidOperationException();
        }
        return operationEnum;
    }

}
