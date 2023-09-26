package com.stock.exchange.repository;

import com.stock.exchange.models.MonthlyLimit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonthlyLimitRepository extends JpaRepository<MonthlyLimit, Long> {

    Object findByCategory(String items);

    Optional<MonthlyLimit> findTopByCategoryOrderByDateSetDesc(String category);
}
