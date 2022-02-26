package com.gbm.challenge.gbmchallenge.repository;

import com.gbm.challenge.gbmchallenge.exception.business.InvalidAccountException;
import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    default AccountEntity createAccountAndFlush(Double balance) {
        AccountEntity entity = new AccountEntity(balance);
        return saveAndFlush(entity);
    }

    default AccountEntity findAccountById(String accountId) {
        Optional<AccountEntity> account = findById(accountId);
        if (account.isEmpty()) {
            throw new InvalidAccountException();
        }
        return account.get();
    }

}
