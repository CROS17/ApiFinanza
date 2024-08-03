package com.example.apifinance.model.dto;

import java.time.LocalDate;

public class ExchangeRateDTO {

    private LocalDate date;
    private Double purchaseAmount;
    private Double saleAmount;
    private Long typeCurrencyId;
    private Boolean status;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Double purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Long getTypeCurrencyId() {
        return typeCurrencyId;
    }

    public void setTypeCurrencyId(Long typeCurrencyId) {
        this.typeCurrencyId = typeCurrencyId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
