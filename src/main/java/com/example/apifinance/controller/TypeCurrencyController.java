package com.example.apifinance.controller;

import com.example.apifinance.model.ExchangeRate;
import com.example.apifinance.model.TypeCurrency;
import com.example.apifinance.service.TypeCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/typecurrency")
public class TypeCurrencyController {
    @Autowired
    private TypeCurrencyService typeCurrencyService;

    @PostMapping
    public ResponseEntity<TypeCurrency> addTypeCurrency(@RequestBody TypeCurrency typeCurrency) {
        try{
            TypeCurrency createTypeCurrency = typeCurrencyService.createTypeCurrency(typeCurrency);
            return new ResponseEntity<>(createTypeCurrency, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<TypeCurrency>> getAllExchangeRate(){
        List<TypeCurrency> typeCurrency = typeCurrencyService.getAllTypeCurrency();
        return new ResponseEntity<>(typeCurrency, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeCurrency> getExchangeRateById(@PathVariable Long id) {
        Optional<TypeCurrency> typeCurrency = typeCurrencyService.getTypeCurrencyById(id);
        return typeCurrency.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TypeCurrency> updateExchangeRate(@PathVariable Long id, @RequestBody TypeCurrency typeCurrency) {
        TypeCurrency updateTypeCurrency = typeCurrencyService.updateTypeCurrency(id, typeCurrency);
        return updateTypeCurrency != null ? ResponseEntity.ok(updateTypeCurrency) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TypeCurrency> deleteExchangeRate(@PathVariable Long id) {
        typeCurrencyService.deleteTypeCurrency(id);
        return ResponseEntity.noContent().build();
    }
}
