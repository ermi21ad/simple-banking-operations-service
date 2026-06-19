package com.ermias.bankingservice.bankingservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


public class TransactionRequest {
    @NotNull
    @Size(min=1, max=100)
    private String accountNumber;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    @Size(min = 1, max = 255)
    private String narration;

    public TransactionRequest() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }
}
