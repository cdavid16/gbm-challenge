package com.gbm.challenge.gbmchallenge.repository;

import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    default AccountEntity createAccountAndFlush(Double balance) {
        AccountEntity entity = new AccountEntity(balance);
        return save(entity);
    }
}
