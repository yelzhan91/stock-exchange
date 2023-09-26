package com.stock.exchange.services.impl;

import com.stock.exchange.models.MonthlyLimit;
import com.stock.exchange.repository.MonthlyLimitRepository;
import com.stock.exchange.services.LimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {


    private final MonthlyLimitRepository monthlyLimitRepository;

    @Override
    public void setNewLimit(MonthlyLimit limit) {
        limit.setDateSet(LocalDateTime.now());
        monthlyLimitRepository.save(limit);
    }

    @Override
    public Optional<MonthlyLimit> getCurrentLimitForCategory(String category) {
        return monthlyLimitRepository.findTopByCategoryOrderByDateSetDesc(category);
    }
}
