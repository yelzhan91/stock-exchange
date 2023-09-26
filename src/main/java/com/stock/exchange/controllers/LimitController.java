package com.stock.exchange.controllers;

import com.stock.exchange.models.MonthlyLimit;
import com.stock.exchange.services.LimitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/limit")
@Tag(name = "Monthly Limits", description = "API для месячных лимитов")
public class LimitController {


    private final LimitService limitService;

    @PostMapping
    @Operation(summary = "Установка нового месячного лимита",
            description = "Этот метод позволяет установить новый месячный лимит")
    public ResponseEntity<String> setNewLimit(@RequestBody MonthlyLimit limit) {
        limitService.setNewLimit(limit);
        return ResponseEntity.ok("Limit set successfully.");
    }

    @GetMapping("/current")
    @Operation(summary = "Получение текущего месячного лимита",
            description = "Метод возвращает текущий месячный лимит для указанной категории")
    public ResponseEntity<MonthlyLimit> getCurrentLimit(@RequestParam String category) {
        Optional<MonthlyLimit> limit = limitService.getCurrentLimitForCategory(category);
        return limit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
