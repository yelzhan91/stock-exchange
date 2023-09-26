package com.stock.exchange.services;

import com.stock.exchange.models.ExchangeRate;

import java.time.LocalDate;
import java.util.Optional;

public interface ExchangeRateService {

    Optional<ExchangeRate> getExchangeRateForDate(String currency, LocalDate date);

    ExchangeRate fetchFromExternalService(String currency, LocalDate date);
}
