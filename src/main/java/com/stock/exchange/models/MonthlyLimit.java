package com.stock.exchange.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Data
public class MonthlyLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private BigDecimal limitAmount = new BigDecimal("1000");
    private LocalDateTime dateSet = LocalDateTime.now();


    public MonthlyLimit() {
    }

    public MonthlyLimit(String category, BigDecimal limitAmount) {
        this.category = category;
        this.limitAmount = limitAmount;
    }


    @Override
    public String toString() {
        return "MonthlyLimit{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", limitAmount=" + limitAmount +
                ", dateSet=" + dateSet +
                '}';
    }

    public void setDateSet(LocalDateTime now) {
    }
}
