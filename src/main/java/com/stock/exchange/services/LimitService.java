package com.stock.exchange.services;
import com.stock.exchange.models.MonthlyLimit;
import java.util.Optional;

public interface LimitService {
    void setNewLimit(MonthlyLimit limit);

    Optional<MonthlyLimit> getCurrentLimitForCategory(String category);
}
