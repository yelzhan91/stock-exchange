package com.stock.exchange.controllers;

import com.stock.exchange.models.ExchangeRate;
import com.stock.exchange.services.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rate")
@Tag(name = "Exchange Rate", description = "API для обменных курсов")
public class ExchangeRateController {


    private final ExchangeRateService exchangeRateService;

    @PostMapping
    @Operation(summary = "Сохранение обменного курса",
            description = "Этот метод позволяет сохранить новый обменный курс")
    public ResponseEntity<String> saveRate(@RequestBody ExchangeRate rate) {
        // Получаем курс из внешнего источника и сохраняем его
        ExchangeRate fetchedRate = exchangeRateService.fetchFromExternalService(rate.getCurrency(), rate.getDate());
        return ResponseEntity.ok("Exchange rate saved successfully for " + rate.getCurrency());
    }

    @GetMapping("/current/{currency}")
    @Operation(summary = "Получение текущего курса валюты",
            description = "Метод возвращает текущий обменный курс для указанной валюты")
    public ResponseEntity<ExchangeRate> getCurrentRateForCurrency(@PathVariable String currency) {
        // Получаем текущий курс для валюты на сегодняшний день
        Optional<ExchangeRate> rate = exchangeRateService.getExchangeRateForDate(currency, LocalDate.now());
        return rate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
