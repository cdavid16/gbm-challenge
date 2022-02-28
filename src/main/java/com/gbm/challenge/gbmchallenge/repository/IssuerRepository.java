package com.gbm.challenge.gbmchallenge.repository;

import com.gbm.challenge.gbmchallenge.exception.business.InvalidIssuerException;
import com.gbm.challenge.gbmchallenge.exception.business.TooManyIssuerException;
import com.gbm.challenge.gbmchallenge.model.entities.IssuerEntity;
import com.gbm.challenge.gbmchallenge.utils.CollectionsHelper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssuerRepository extends JpaRepository<IssuerEntity, String> {

    List<IssuerEntity> findByName(String name);

    default IssuerEntity findIssuerByName(String issuerName) {
        List<IssuerEntity> issuerList = findByName(issuerName);
        if (CollectionsHelper.isEmptyOrNull(issuerList)) {
            throw new InvalidIssuerException();
        }
        if (issuerList.size()>1) {
            throw new TooManyIssuerException();
        }
        return issuerList.get(0);
    }

}
