package com.stock.exchange.services.impl;

import com.stock.exchange.models.ExchangeRate;
import com.stock.exchange.repository.ExchangeRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class ExchangeRateServiceImplTest {

    @InjectMocks
    private ExchangeRateServiceImpl service;

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnStoredExchangeRate() {
        ExchangeRate mockRate = new ExchangeRate("USD", LocalDate.now(), 1.0);
        when(exchangeRateRepository.findByCurrencyAndDate(anyString(), any())).thenReturn(mockRate);

        Optional<ExchangeRate> result = service.getExchangeRateForDate("USD", LocalDate.now());

        assertTrue(result.isPresent());
        assertEquals(mockRate, result.get());
        verify(restTemplate, never()).getForObject(anyString(), eq(ExchangeRate.class), anyString(), any());
    }

    @Test
    void shouldFetchFromExternalServiceWhenNotStored() {
        when(exchangeRateRepository.findByCurrencyAndDate(anyString(), any())).thenReturn(null);

        ExchangeRate mockRate = new ExchangeRate("USD", LocalDate.now(), 1.0);
        when(restTemplate.getForObject(anyString(), eq(ExchangeRate.class), anyString(), any())).thenReturn(mockRate);

        Optional<ExchangeRate> result = service.getExchangeRateForDate("USD", LocalDate.now());

        assertTrue(result.isPresent());
        assertEquals(mockRate, result.get());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(ExchangeRate.class), anyString(), any());
    }

    @Test
    void shouldThrowExceptionWhenFailedToFetchFromService() {
        when(exchangeRateRepository.findByCurrencyAndDate(anyString(), any())).thenReturn(null);
        when(restTemplate.getForObject(anyString(), eq(ExchangeRate.class), anyString(), any())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> service.getExchangeRateForDate("USD", LocalDate.now()));
    }
}




