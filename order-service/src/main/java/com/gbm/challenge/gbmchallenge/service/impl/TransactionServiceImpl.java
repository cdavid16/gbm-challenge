package com.gbm.challenge.gbmchallenge.service.impl;

import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import com.gbm.challenge.gbmchallenge.model.entities.*;
import com.gbm.challenge.gbmchallenge.model.request.OperationDto;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.repository.AccountRepository;
import com.gbm.challenge.gbmchallenge.repository.AccountStockRepository;
import com.gbm.challenge.gbmchallenge.repository.IssuerRepository;
import com.gbm.challenge.gbmchallenge.repository.TransactionRepository;
import com.gbm.challenge.gbmchallenge.service.TransactionService;
import com.gbm.challenge.gbmchallenge.utils.NumberHelper;
import com.gbm.challenge.gbmchallenge.utils.OperationEnumHelper;
import com.gbm.challenge.gbmchallenge.validator.OrderValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.transaction.Transactional;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gbm.challenge.gbmchallenge.utils.AccountStockHelper.*;
import static com.gbm.challenge.gbmchallenge.utils.OperationHelper.computeAmountApplied;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final IssuerRepository issuerRepository;
    private final AccountStockRepository accountStockRepository;
    private final OrderValidator orderValidator;

    @Autowired
    public TransactionServiceImpl(final AccountRepository accountRepository,
                                  final TransactionRepository transactionRepository,
                                  final IssuerRepository issuerRepository,
                                  final AccountStockRepository accountStockRepository,
                                  final OrderValidator orderValidator) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.issuerRepository = issuerRepository;
        this.accountStockRepository = accountStockRepository;
        this.orderValidator = orderValidator;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public TransactionEntity processTransaction(final SendOrderDto orderRequest,
                                                final String accountId,
                                                final WebRequest webRequest) {
        log.info("Processing order {} for accountId: {}", orderRequest, accountId);
        Validate.notNull(orderRequest);
        Validate.notNull(accountId);
        Validate.notNull(webRequest);
        AccountEntity account = this.accountRepository.findAccountById(accountId);
        webRequest.setAttribute("cash", account.getBalance(), RequestAttributes.SCOPE_REQUEST);
        orderValidator.validate(orderRequest, account);
        TransactionEntity transaction = createTransaction(account, orderRequest.getTimestamp());
        log.info("The transaction header {} was created successfully.", transaction);
        Double balance = processOrderDetailsAndGetOrderBalance(orderRequest, transaction);
        log.info("Transaction details were created successfully. Transaction={}", transaction);
        updateAccountBalance(account, balance);
        updateAccountStock(transaction,
                transaction.getTransactionDetails()
                        .stream()
                        .map(td -> td.getIssuer().getName())
                        .collect(Collectors.toSet()));
        log.info("Account and stock balances were updated successfully.");
        this.commitTransaction(account, transaction);
        log.info("DB Has committed the transactions.");
        return transaction;
    }

    @Override
    public TransactionEntity createTransaction(final AccountEntity account, final Long timestamp) {
        Validate.notNull(account);
        Validate.notNull(timestamp);
        return this.transactionRepository.createTransaction(account, timestamp);
    }

    @Override
    public TransactionDetailEntity createTransactionDetails(final TransactionEntity transaction,
                                                            final String issuerName,
                                                            final OperationEnum operation,
                                                            final Long quantity,
                                                            final Double price) {
        Validate.notNull(transaction, "Transaction cannot be null");
        Validate.notNull(issuerName, "Issuer name cannot be null");
        Validate.notNull(operation, "Operation cannot be null");
        NumberHelper.isPositive(quantity);
        NumberHelper.isPositive(price);
        IssuerEntity issuer = this.issuerRepository.findIssuerByName(issuerName);
        return this.transactionRepository.createTransactionDetail(transaction, issuer, operation, quantity, price);
    }

    public void updateAccountStock(final TransactionEntity transaction, final Set<String> issuers) {
        AccountEntity account = transaction.getAccount();
        Map<String, AccountStockEntity> currentStocks =
                buildBalanceMap(account.getAccountStocks(), ase -> issuers.contains(ase.getIssuer().getName()));
        for(TransactionDetailEntity transactionDetail : transaction.getTransactionDetails()) {
            String issuerName = transactionDetail.getIssuer().getName();
            mergeAccountStock(transactionDetail, issuerName, currentStocks);
        }
    }

    public void updateAccountBalance(final AccountEntity account, final Double amountToApply) {
        Double currentBalance = account.getBalance();
        account.setBalance(currentBalance + amountToApply);
    }

    private double processOrderDetailsAndGetOrderBalance(final SendOrderDto orderRequest,
                                                         final TransactionEntity transaction) {
        double balance = 0.0;
        for (OperationDto operation : orderRequest.getOperations()) {
            OperationEnum operationEnum = OperationEnumHelper.parseStringToEnum(operation.getOperation());
            createTransactionDetails(transaction, operation.getIssuer(),
                    operationEnum, operation.getTotalShares(), operation.getSharePrice());
            balance += computeAmountApplied(operationEnum, operation.getTotalShares(), operation.getSharePrice());
        }
        return balance;
    }

    private void mergeAccountStock(final TransactionDetailEntity transactionDetail,
                                   final String issuerName,
                                   final Map<String, AccountStockEntity> currentStocks) {
        if (currentStocks.containsKey(issuerName)) {
            AccountStockEntity currentPosition = currentStocks.get(issuerName);
            updateStockBalance(transactionDetail.getOperation(), currentPosition, transactionDetail.getQuantity());
        } else {
            this.accountStockRepository.createAccountStockEntity(transactionDetail.getQuantity(),
                    transactionDetail.getTransaction().getAccount(), transactionDetail.getIssuer());
        }
    }

    @Transactional(rollbackOn = Exception.class)
    private void commitTransaction(AccountEntity account, TransactionEntity transaction) {
        this.accountRepository.saveAndFlush(account);
        this.transactionRepository.saveAndFlush(transaction);
    }
}
