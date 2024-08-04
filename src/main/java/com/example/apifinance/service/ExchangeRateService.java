package com.example.apifinance.service;

import com.example.apifinance.model.ExchangeRate;
import com.example.apifinance.model.dto.ExchangeRateDTO;
import com.example.apifinance.repository.ExchangeRateRepository;
import com.example.apifinance.repository.TypeCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apifinance.model.TypeCurrency;

import java.util.List;
import java.util.Optional;
import java.time.*;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private TypeCurrencyRepository typeCurrencyRepository;

    /*validate register*/
    public boolean existsByDateAndTypeCurrency(LocalDate date, TypeCurrency typeCurrencyId) {
        return exchangeRateRepository.existsByDateAndTypeCurrencyId(date, typeCurrencyId);
    }

    /* Create and configure ExchangeRate */
    public ExchangeRate createExchangeRate(ExchangeRateDTO exchangeRateDTO) {
        TypeCurrency typeCurrency = typeCurrencyRepository.findById(exchangeRateDTO.getTypeCurrencyId())
                .orElseThrow(() -> new IllegalArgumentException("Type currency not found"));

        if (existsByDateAndTypeCurrency(exchangeRateDTO.getDate(), typeCurrency)) {
            throw new IllegalStateException("Exchange rate already exists for the given date and type currency");
        }

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setDate(exchangeRateDTO.getDate());
        exchangeRate.setPurchaseAmount(exchangeRateDTO.getPurchaseAmount());
        exchangeRate.setSaleAmount(exchangeRateDTO.getSaleAmount());
        exchangeRate.setTypeCurrency(typeCurrency); // Asigna el objeto TypeCurrency al campo typeCurrencyId
        exchangeRate.setStatus(exchangeRateDTO.getStatus() != null ? exchangeRateDTO.getStatus() : true);

        return exchangeRateRepository.save(exchangeRate);
    }

    public List<ExchangeRate> getAllExchangeRates(){
        return exchangeRateRepository.findAll();
    }

    public Optional<ExchangeRate> getExchangeRateById(Long id) {
        return exchangeRateRepository.findById(id);
    }

    public ExchangeRate updateExchangeRate(Long id, ExchangeRate exchangeRateDetails) {
        return exchangeRateRepository.findById(id).map(existingExchangeRate -> {

            // Actualizar campos
            if (exchangeRateDetails.getDate() != null && !exchangeRateDetails.getDate().equals(existingExchangeRate.getDate())) {
                existingExchangeRate.setDate(exchangeRateDetails.getDate());
            }
            if (exchangeRateDetails.getPurchaseAmount() != null && !exchangeRateDetails.getPurchaseAmount().equals(existingExchangeRate.getPurchaseAmount())) {
                existingExchangeRate.setPurchaseAmount(exchangeRateDetails.getPurchaseAmount());
            }
            if (exchangeRateDetails.getSaleAmount() != null && !exchangeRateDetails.getSaleAmount().equals(existingExchangeRate.getSaleAmount())) {
                existingExchangeRate.setSaleAmount(exchangeRateDetails.getSaleAmount());
            }
            if (exchangeRateDetails.getTypeCurrency() != null && !exchangeRateDetails.getTypeCurrency().equals(existingExchangeRate.getTypeCurrency())) {
                existingExchangeRate.setTypeCurrency(exchangeRateDetails.getTypeCurrency());
            }
            if (exchangeRateDetails.getStatus() != null && !exchangeRateDetails.getStatus().equals(existingExchangeRate.getStatus())) {
                existingExchangeRate.setStatus(exchangeRateDetails.getStatus());
            }

            // Guardar la entidad actualizada
            return exchangeRateRepository.save(existingExchangeRate);
        }).orElse(null);
    }

    public void deleteExchangeRate(Long id) {
        exchangeRateRepository.deleteById(id);
    }
}
