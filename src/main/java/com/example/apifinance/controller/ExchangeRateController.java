package com.example.apifinance.controller;

import com.example.apifinance.model.ExchangeRate;
import com.example.apifinance.model.TypeCurrency;
import com.example.apifinance.model.dto.ExchangeRateDTO;
import com.example.apifinance.repository.TypeCurrencyRepository;
import com.example.apifinance.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exchanges")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private TypeCurrencyRepository typeCurrencyRepository;

    @PostMapping
    public ResponseEntity<ExchangeRate> addExchangeRate(@RequestBody ExchangeRateDTO exchangeRateDTO) {
        try {
            // Crea y guarda el ExchangeRate a través del servicio
            ExchangeRate savedExchangeRate = exchangeRateService.createExchangeRate(exchangeRateDTO);
            return new ResponseEntity<>(savedExchangeRate, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping
    public ResponseEntity<List<ExchangeRate>> getAllExchangeRate(){
        List<ExchangeRate> exchangeRate = exchangeRateService.getAllExchangeRates();
        return new ResponseEntity<>(exchangeRate, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExchangeRate> getExchangeRateById(@PathVariable Long id) {
        Optional<ExchangeRate> exchangeRate = exchangeRateService.getExchangeRateById(id);
        return exchangeRate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExchangeRate> updateExchangeRate(@PathVariable Long id, @RequestBody ExchangeRate exchangeRate) {
        try{
            ExchangeRate updateExchangeRate = exchangeRateService.updateExchangeRate(id, exchangeRate);
            return updateExchangeRate != null ? ResponseEntity.ok(updateExchangeRate) : ResponseEntity.notFound().build();
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExchangeRate> deleteExchangeRate(@PathVariable Long id) {
        exchangeRateService.deleteExchangeRate(id);
        return ResponseEntity.noContent().build();
    }
}
