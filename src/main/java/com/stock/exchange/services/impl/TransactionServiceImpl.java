package com.stock.exchange.services.impl;
import com.stock.exchange.models.ExchangeRate;
import com.stock.exchange.models.MonthlyLimit;
import com.stock.exchange.models.Transaction;
import com.stock.exchange.repository.MonthlyLimitRepository;
import com.stock.exchange.repository.TransactionRepository;
import com.stock.exchange.services.ExchangeRateService;
import com.stock.exchange.services.LimitService;
import com.stock.exchange.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ExchangeRateService exchangeRateService;

    private final MonthlyLimitRepository monthlyLimitRepository;

    private final LimitService limitService;


    @Override
    public void saveTransaction(Transaction transaction, String category) {
        checkLimitExceeded(transaction);
        transactionRepository.save(transaction);

        BigDecimal usdAmount = convertToUSD(transaction.getAmount(), transaction.getCurrency());

        // 3. Проверяем, превышает ли сумма текущий месячный лимит
        Optional<MonthlyLimit> currentLimit = limitService.getCurrentLimitForCategory(category);

        if (usdAmount.compareTo(currentLimit.get().getLimitAmount()) > 0) {
            transaction.setLimitExceeded(true);
            transactionRepository.save(transaction);
        }
    }

    private BigDecimal convertToUSD(BigDecimal amount, String currency) {
        if ("USD".equals(currency)) {
            return amount;
        }

        Optional<ExchangeRate> currentRateOptional = exchangeRateService.getExchangeRateForDate("ffjkw", LocalDate.now());

        if (currentRateOptional.isEmpty()) {
            throw new RuntimeException("Exchange rate for " + currency + " not found.");
        }

        BigDecimal currentRate = currentRateOptional.get().getRateToUSD();
        return amount.divide(currentRate, 2, RoundingMode.HALF_UP);  // Assuming 2 decimal places for USD amounts
    }


    @Override
    public void checkLimitExceeded(Transaction transaction) {


        // Простой пример (вам нужно будет дополнить этот код):
        BigDecimal currentLimit = (BigDecimal) monthlyLimitRepository.findByCategory("Товары");

        if (transaction.getAmount().compareTo(currentLimit) > 0) {
            transaction.setLimitExceeded(true);
        }
    }

    // Получение списка транзакций, превысивших лимит
    @Override
    public List<Transaction> listExceededTransactions() {
        return transactionRepository.findAllByLimitExceededTrue();
    }
}
