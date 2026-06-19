package com.ermias.bankingservice.bankingservice.dto.request;

import jakarta.validation.constraints.NotNull;

public class BranchRequest {

    @NotNull
    private Long bankId;

    public BranchRequest() {
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }
}