package com.stock.exchange.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currency;
    private BigDecimal rateToUSD;
    private LocalDate date;
    private BigDecimal closeRate;
    private BigDecimal previousCloseRate;



    public ExchangeRate() {
    }

    public ExchangeRate(String currency, BigDecimal rateToUSD, LocalDate date, BigDecimal closeRate, BigDecimal previousCloseRate) {
        this.currency = currency;
        this.rateToUSD = rateToUSD;
        this.date = date;
        this.closeRate = closeRate;
        this.previousCloseRate = previousCloseRate;
    }

    public ExchangeRate(String eur, BigDecimal valueOf) {

    }

    public ExchangeRate(String usd, LocalDate now, double v) {
    }


    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", rateToUSD=" + rateToUSD +
                ", date=" + date +
                ", closeRate=" + closeRate +
                ", previousCloseRate=" + previousCloseRate +
                '}';
    }
}
