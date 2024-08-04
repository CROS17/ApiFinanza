package com.example.apifinance.service;

import com.example.apifinance.model.TypeCurrency;
import com.example.apifinance.repository.TypeCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeCurrencyService {
    @Autowired
    private TypeCurrencyRepository typeCurrencyRepository;

    public TypeCurrency createTypeCurrency(TypeCurrency typeCurrency) {
        return typeCurrencyRepository.save(typeCurrency);
    }

    public List<TypeCurrency> getAllTypeCurrency() {
        return typeCurrencyRepository.findAll();
    }

    public Optional<TypeCurrency> getTypeCurrencyById(Long id) {
        return typeCurrencyRepository.findById(id);
    }

    public TypeCurrency updateTypeCurrency(Long id, TypeCurrency typeCurrencyDetails) {
        return typeCurrencyRepository.findById(id).map(existingTypeCurrency -> {

            if (typeCurrencyDetails.getSymbol() != null && !typeCurrencyDetails.getSymbol().equals(existingTypeCurrency.getSymbol())){
                existingTypeCurrency.setSymbol(typeCurrencyDetails.getSymbol());
            }

            if(typeCurrencyDetails.getDescription() != null && !typeCurrencyDetails.getDescription().equals(existingTypeCurrency.getDescription())){
                existingTypeCurrency.setDescription(typeCurrencyDetails.getDescription());
            }

            return typeCurrencyRepository.save(existingTypeCurrency);
        }).orElse(null);
    }

    public void deleteTypeCurrency(Long id) {
        typeCurrencyRepository.deleteById(id);
    }
}
