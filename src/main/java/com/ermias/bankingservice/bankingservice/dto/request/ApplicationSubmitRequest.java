package com.ermias.bankingservice.bankingservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ApplicationSubmitRequest {
    @NotNull
    private Long bankId;

    @NotNull
    private Long branchId;

    @NotBlank
    @Size(min = 2, max = 100)
    private String accountName;

    @NotBlank
    @Size(min = 5, max = 50)
    private String accountNumber;

    public ApplicationSubmitRequest() {
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
