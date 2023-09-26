package com.stock.exchange.controllers;

import com.stock.exchange.models.Transaction;
import com.stock.exchange.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
@Tag(name = "Transactions", description = "API для управления транзакциями")
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping
    @Operation(summary = "Сохранение транзакции",
            description = "Этот метод сохраняет новую транзакцию для определенной категории")
    public ResponseEntity<String> saveTransaction(@RequestBody Transaction transaction, String category) {
        transactionService.saveTransaction(transaction, category);
        return ResponseEntity.ok("Transaction saved successfully.");
    }


    @GetMapping("/transactions/exceeded")
    @Operation(summary = "Получение списка транзакций, превысивших лимит",
            description = "Этот метод возвращает список транзакций, которые превысили установленный лимит")
    public ResponseEntity<List<Transaction>> listExceededTransactions() {
        List<Transaction> transactions = transactionService.listExceededTransactions();
        return ResponseEntity.ok(transactions);
    }
}
