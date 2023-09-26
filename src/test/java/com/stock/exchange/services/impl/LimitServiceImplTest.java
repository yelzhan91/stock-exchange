package com.stock.exchange.services.impl;

import com.stock.exchange.models.MonthlyLimit;
import com.stock.exchange.repository.MonthlyLimitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LimitServiceImplTest {

    @InjectMocks
    private LimitServiceImpl limitService;

    @Mock
    private MonthlyLimitRepository monthlyLimitRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSetNewLimit() {
        MonthlyLimit limit = new MonthlyLimit();
        //... fill your MonthlyLimit object if needed

        limitService.setNewLimit(limit);

        verify(limit, times(1)).setDateSet(any(LocalDateTime.class));
        verify(monthlyLimitRepository, times(1)).save(limit);
    }

    @Test
    public void testGetCurrentLimitForCategory() {
        String category = "someCategory";
        MonthlyLimit mockLimit = new MonthlyLimit();
        //... fill your MonthlyLimit object if needed

        when(monthlyLimitRepository.findTopByCategoryOrderByDateSetDesc(category)).thenReturn(Optional.of(mockLimit));

        Optional<MonthlyLimit> result = limitService.getCurrentLimitForCategory(category);

        verify(monthlyLimitRepository, times(1)).findTopByCategoryOrderByDateSetDesc(category);
        assertEquals(Optional.of(mockLimit), result);
    }
}
