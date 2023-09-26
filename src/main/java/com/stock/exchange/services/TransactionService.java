package com.stock.exchange.services;

import com.stock.exchange.models.Transaction;

import java.util.List;

public interface TransactionService {
    void saveTransaction(Transaction transaction, String category);

    void checkLimitExceeded(Transaction transaction);

    List<Transaction> listExceededTransactions();
}
