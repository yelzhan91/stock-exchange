package com.stock.exchange.controllers;

import com.stock.exchange.models.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mockbank")
@Tag(name = "Mock Bank Transactions", description = "API для имитации банковских транзакций")
public class MockBankController {

    @GetMapping("/transactions")
    @Operation(summary = "Получение списка транзакций",
            description = "Этот метод возвращает список имитационных банковских транзакций")
    public ResponseEntity<List<Transaction>> getTransactions() {

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(/*...*/));
        transactions.add(new Transaction(/*...*/));


        return ResponseEntity.ok(transactions);
    }
}
