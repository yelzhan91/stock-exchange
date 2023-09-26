package com.stock.exchange.services.impl;

import com.stock.exchange.models.ExchangeRate;
import com.stock.exchange.models.Transaction;
import com.stock.exchange.repository.MonthlyLimitRepository;
import com.stock.exchange.repository.TransactionRepository;
import com.stock.exchange.services.ExchangeRateService;
import com.stock.exchange.services.LimitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ExchangeRateService exchangeRateService;

    @Mock
    private MonthlyLimitRepository monthlyLimitRepository;

    @Mock
    private LimitService limitService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setCurrency("EUR");

        when(exchangeRateService.getExchangeRateForDate("EUR", LocalDate.now()))
                .thenReturn(Optional.of(new ExchangeRate("EUR", BigDecimal.valueOf(0.85))));  // Example rate



        transactionService.saveTransaction(transaction, "SomeCategory");

        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void testListExceededTransactions() {
        transactionService.listExceededTransactions();
        verify(transactionRepository, times(1)).findAllByLimitExceededTrue();
    }


}


