package com.gbm.challenge.gbmchallenge.service.impl;

import com.gbm.challenge.gbmchallenge.BaseTestWithData;
import com.gbm.challenge.gbmchallenge.enums.OperationEnum;
import com.gbm.challenge.gbmchallenge.exception.business.InvalidAccountException;
import com.gbm.challenge.gbmchallenge.exception.business.InvalidIssuerException;
import com.gbm.challenge.gbmchallenge.exception.business.TooManyIssuerException;
import com.gbm.challenge.gbmchallenge.mockdata.SendOrderDtoValidData;
import com.gbm.challenge.gbmchallenge.model.entities.AccountEntity;
import com.gbm.challenge.gbmchallenge.model.entities.TransactionEntity;
import com.gbm.challenge.gbmchallenge.model.request.SendOrderDto;
import com.gbm.challenge.gbmchallenge.repository.AccountRepository;
import com.gbm.challenge.gbmchallenge.repository.AccountStockRepository;
import com.gbm.challenge.gbmchallenge.repository.IssuerRepository;
import com.gbm.challenge.gbmchallenge.repository.TransactionRepository;
import com.gbm.challenge.gbmchallenge.service.TransactionService;
import com.gbm.challenge.gbmchallenge.validator.OrderValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest extends BaseTestWithData {

    private TransactionService service;
    private final AccountRepository accountRepository = mock(AccountRepository.class);
    private final TransactionRepository transactionRepository = mock(TransactionRepository.class);
    private final IssuerRepository issuerRepository = mock(IssuerRepository.class);
    private final AccountStockRepository accountStockRepository = mock(AccountStockRepository.class);
    private final OrderValidator orderValidator = mock(OrderValidator.class);

    @BeforeEach
    void setup() {
        service = new TransactionServiceImpl(accountRepository, transactionRepository,
                issuerRepository, accountStockRepository, orderValidator);
    }

    @Test
    void createTransactionShouldThrowIllegalArgumentExceptionWhenAccountIsNull() {
        assertThrows(IllegalArgumentException.class, () -> service.createTransaction(null, 0L));
    }

    @Test
    void createTransactionShouldThrowIllegalArgumentExceptionWhenTimestampIsNull() {
        assertThrows(IllegalArgumentException.class, () -> service.createTransaction(accountData.getDummyAccount(),
                null));
    }

    @Test
    void createTransactionShouldSucceedWhenAreValidAccountAndTimestamp() {
        AccountEntity account = accountData.getDummyAccount();
        Long timestamp = 1645726301000L;
        service.createTransaction(accountData.getDummyAccount(), timestamp);
        verify(transactionRepository, times(1)).createTransaction(account, timestamp);
    }

    @Test
    void createTransactionDetailsShouldThrowIllegalArgumentExceptionWhenTransactionIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createTransactionDetails(null,"",
                        OperationEnum.SELL, 0L, 0.0));
    }

    @Test
    void createTransactionDetailsShouldThrowIllegalArgumentExceptionWhenIssuerNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createTransactionDetails(mock(TransactionEntity.class),null,
                        OperationEnum.SELL, 0L, 0.0));
    }

    @Test
    void createTransactionDetailsShouldThrowIllegalArgumentExceptionWhenOperationIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createTransactionDetails(mock(TransactionEntity.class),"",
                        null, 0L, 0.0));
    }

    @Test
    void createTransactionDetailsShouldThrowIllegalArgumentExceptionWhenQuantityIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createTransactionDetails(mock(TransactionEntity.class),"",
                        OperationEnum.SELL, null, 0.0));
    }

    @Test
    void createTransactionDetailsShouldThrowIllegalArgumentExceptionWhenQuantityIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createTransactionDetails(mock(TransactionEntity.class),"",
                        OperationEnum.SELL, -20L, 0.0));
    }

    @Test
    void createTransactionDetailsShouldThrowIllegalArgumentExceptionWhenPriceIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createTransactionDetails(mock(TransactionEntity.class),"",
                        OperationEnum.SELL, 0L, null));
    }

    @Test
    void createTransactionDetailsShouldThrowIllegalArgumentExceptionWhenPriceIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> service.createTransactionDetails(mock(TransactionEntity.class),"",
                        OperationEnum.SELL, 0L, -1.0));
    }

    @Test
    void createTransactionDetailsShouldThrowInvalidIssuerExceptionWhenIssuerDoesNotExist() {
        String issuerName = "Invalid";
        when(issuerRepository.findIssuerByName(issuerName)).thenThrow(InvalidIssuerException.class);
        assertThrows(InvalidIssuerException.class,
                () -> service.createTransactionDetails(mock(TransactionEntity.class),issuerName,
                        OperationEnum.SELL, 0L, 0.0));
    }

    @Test
    void createTransactionDetailsShouldThrowTooManyIssuerExceptionWhenThereAreMoreThanOneIssuerWithSameName() {
        String issuerName = "NFTX";
        when(issuerRepository.findIssuerByName(issuerName)).thenThrow(TooManyIssuerException.class);
        assertThrows(TooManyIssuerException.class,
                () -> service.createTransactionDetails(mock(TransactionEntity.class),issuerName,
                        OperationEnum.SELL, 0L, 0.0));
    }

    @Test
    void createTransactionDetailsShouldSucceedWhenDataIsValid() {
        String issuerName = "NFTX";
        when(issuerRepository.findIssuerByName(issuerName)).thenReturn(issuerData.NFTX);
        TransactionEntity transaction = transactionData.TRANSACTION_1;
        Long quantity = 1L;
        Double price = 20.0;
        service.createTransactionDetails(transaction, issuerName, OperationEnum.SELL, quantity, price);
        verify(transactionRepository, times(1)).createTransactionDetail(
                transaction, issuerData.NFTX, OperationEnum.SELL, quantity, price);
    }

    @Test
    void processTransactionShouldThrowIllegalArgumentExceptionWhenOrderRequestIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.processTransaction(null, "", mock(WebRequest.class)));
    }

    @Test
    void processTransactionShouldThrowIllegalArgumentExceptionWhenAccountIdIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.processTransaction(mock(SendOrderDto.class), null, mock(WebRequest.class)));
    }

    @Test
    void processTransactionShouldThrowIllegalArgumentExceptionWhenWebRequestIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> service.processTransaction(mock(SendOrderDto.class), "", null));
    }

    @Test
    void processTransactionShouldThrowInvalidAccountExceptionWhenAccountDoesNotExist() {
        String accountId = "2";
        WebRequest webRequest = mock(WebRequest.class);
        SendOrderDto order = mock(SendOrderDto.class);

        when(accountRepository.findAccountById(accountId)).thenThrow(InvalidAccountException.class);

        assertThrows(InvalidAccountException.class,
                () -> service.processTransaction(order, accountId, webRequest));
    }

    @Test
    void processTransactionShouldSucceedWhenOrderIsSingleBuy() {
        String accountId = "1";
        WebRequest webRequest = mock(WebRequest.class);
        SendOrderDto order = SendOrderDtoValidData.getSingleOrderBuy();
        Long timestamp = 1645813301000L;
        AccountEntity account = accountData.getDummyAccount();

        when(accountRepository.findAccountById(accountId)).thenReturn(account);
        when(transactionRepository.createTransaction(account, timestamp)).thenReturn(transactionData.TRANSACTION_1);

        service.processTransaction(order, accountId, webRequest);
        verify(accountRepository, times(1)).saveAndFlush(account);
        verify(transactionRepository, times(1)).saveAndFlush(transactionData.TRANSACTION_1);
    }

    @Test
    void processTransactionShouldSucceedWhenOrderIsSingleSell() {
        String accountId = "1";
        WebRequest webRequest = mock(WebRequest.class);
        SendOrderDto order = SendOrderDtoValidData.getSingleOrderSell();
        Long timestamp = 1645813301000L;
        AccountEntity account = accountData.getDummyAccount();

        when(accountRepository.findAccountById(accountId)).thenReturn(account);
        when(transactionRepository.createTransaction(account, timestamp)).thenReturn(transactionData.TRANSACTION_1);

        service.processTransaction(order, accountId, webRequest);
        verify(accountRepository, times(1)).saveAndFlush(account);
        verify(transactionRepository, times(1)).saveAndFlush(transactionData.TRANSACTION_1);
    }

    @Test
    void processTransactionShouldSucceedWhenOrderIsMultiOrder() {
        String accountId = "1";
        WebRequest webRequest = mock(WebRequest.class);
        SendOrderDto order = SendOrderDtoValidData.getMultiOrder();
        Long timestamp = 1645813301000L;
        AccountEntity account = accountData.getDummyAccount();

        when(accountRepository.findAccountById(accountId)).thenReturn(account);
        when(transactionRepository.createTransaction(account, timestamp)).thenReturn(transactionData.TRANSACTION_1);

        service.processTransaction(order, accountId, webRequest);
        verify(accountRepository, times(1)).saveAndFlush(account);
        verify(transactionRepository, times(1)).saveAndFlush(transactionData.TRANSACTION_1);
    }
}