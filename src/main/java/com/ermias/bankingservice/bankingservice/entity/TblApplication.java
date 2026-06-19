package com.ermias.bankingservice.bankingservice.entity;

import com.ermias.bankingservice.bankingservice.model.base.BaseEntity;
import com.ermias.bankingservice.bankingservice.model.enums.ApplicationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_application")
public class TblApplication extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "branch_name", nullable = false)
    private String branchName;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "account_name", nullable = false)
    private String accountName;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
