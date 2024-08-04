package com.example.apifinance.repository;

import com.example.apifinance.model.ExchangeRate;
import com.example.apifinance.model.TypeCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.*;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate,Long> {
    boolean existsByDateAndTypeCurrencyId(LocalDate date, TypeCurrency typeCurrencyId);
}
