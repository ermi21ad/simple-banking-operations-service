package com.ermias.bankingservice.bankingservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ReversalRequest {

    @NotBlank
    @Size(max = 50)
    private String transactionId;

    @NotBlank
    @Size(min = 5, max = 255)
    private String reason;

    public ReversalRequest() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}