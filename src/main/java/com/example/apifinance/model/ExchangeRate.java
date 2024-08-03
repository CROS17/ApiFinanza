package com.example.apifinance.model;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name="exchange_rates")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;

    @Column(name = "purchase_amount", nullable = false)
    private Double purchaseAmount = 0.0;

    @Column(name = "sale_amount", nullable = false)
    private Double saleAmount = 0.0;

    @ManyToOne
    @JoinColumn(name = "type_currency_id", nullable = false)
    private TypeCurrency typeCurrencyId;

    @Column(nullable = false)
    private Boolean status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        if (purchaseAmount != null) {
            this.purchaseAmount = purchaseAmount;
        }
    }

    public Double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Double saleAmount) {
        if (saleAmount != null) {
            this.saleAmount = saleAmount;
        }
    }

    public TypeCurrency getTypeCurrency() {
        return typeCurrencyId;
    }

    public void setTypeCurrency(TypeCurrency typeCurrencyId) {
        if (typeCurrencyId != null) {
            this.typeCurrencyId = typeCurrencyId;
        }
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = true; // Establece el valor predeterminado a true
        }

        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        this.updatedAt = LocalDateTime.now();

        if (this.date == null) {
            this.date = LocalDate.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
