package com.gbm.challenge.gbmchallenge.repository;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.AccountStockEntity;
import com.gbm.challenge.gbmchallenge.model.entities.IssuerEntity;
import com.gbm.challenge.gbmchallenge.utils.CollectionsHelper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStockEntity,
        AccountStockEntity.AccountStockEntityId> {

    default void createAccountStockEntity(Long quantity, AccountEntity account, IssuerEntity issuer) {
        AccountStockEntity entity = new AccountStockEntity(quantity, account, issuer);

        if (CollectionsHelper.isEmptyOrNull(account.getAccountStocks())) {
            account.setAccountStocks(new HashSet<>());
        }
        account.getAccountStocks().add(entity);
    }

}
