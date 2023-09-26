package com.stock.exchange.repository;
import java.time.LocalDate;
import java.util.Optional;

import com.stock.exchange.models.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    ExchangeRate findByCurrencyAndDate(String currency, LocalDate date);

    Optional<ExchangeRate> findTopByCurrencyOrderByDateDesc(String currency);
}
