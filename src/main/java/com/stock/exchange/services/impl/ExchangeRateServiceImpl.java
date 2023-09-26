package com.stock.exchange.services.impl;

import com.stock.exchange.models.ExchangeRate;
import com.stock.exchange.repository.ExchangeRateRepository;
import com.stock.exchange.services.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {


    private final ExchangeRateRepository exchangeRateRepository;
    private final RestTemplate restTemplate;

    private static final String EXTERNAL_API_URL = "https://api.example.com/getExchangeRate";

    @Override
    public Optional<ExchangeRate> getExchangeRateForDate(String currency, LocalDate date) {
        Optional<ExchangeRate> storedRate = Optional.ofNullable(exchangeRateRepository.findByCurrencyAndDate(currency, date));

        if (storedRate.isPresent()) {
            return storedRate;
        }
        ExchangeRate fetchedRate = fetchFromExternalService(currency, date);
        exchangeRateRepository.save(fetchedRate);

        return Optional.of(fetchedRate);
    }

    @Override
    public ExchangeRate fetchFromExternalService(String currency, LocalDate date) {
        ExchangeRate rate = restTemplate.getForObject(EXTERNAL_API_URL, ExchangeRate.class, currency, date);
        if (rate == null) {
            throw new RuntimeException("Failed to fetch exchange rate for " + currency + " on " + date);
        }
        return rate;
    }
}
