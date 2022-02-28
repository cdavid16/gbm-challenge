package com.gbm.challenge.gbmchallenge.mockdata;

import com.gbm.challenge.gbmchallenge.model.entities.IssuerEntity;

import java.util.Set;

public class IssuerData {

    public static final String AAPL_NAME = "AAPL";
    public static final String AMZN_NAME = "AMZN";
    public static final String NFTX_NAME = "NFTX";

    public final IssuerEntity AAPL;
    public final IssuerEntity AMZN;
    public final IssuerEntity NFTX;
    public final Set<IssuerEntity> issuers;

    public IssuerData() {
        AAPL = new IssuerEntity("1", AAPL_NAME, null, null);
        AMZN = new IssuerEntity("2", AMZN_NAME, null, null);
        NFTX = new IssuerEntity("3", NFTX_NAME, null, null);
        issuers = Set.of(AAPL, AMZN, NFTX);
    }



}
