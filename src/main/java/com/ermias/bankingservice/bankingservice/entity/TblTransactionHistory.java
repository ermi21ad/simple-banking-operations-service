package com.ermias.bankingservice.bankingservice.entity;

import com.ermias.bankingservice.bankingservice.model.base.BaseEntity;
import com.ermias.bankingservice.bankingservice.model.enums.ApplicationStatus;
import com.ermias.bankingservice.bankingservice.model.enums.TransactionHistoryStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_transaction_history")
public class TblTransactionHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String value;

    @Column(name = "transactionId",unique = true,nullable = false)
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionHistoryStatus status;

    @Column(name = "account_number",nullable = false)
    private String accountNumber;

    @Column(nullable = false,precision = 19,scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private String narration;

    @Column(name = "reversal_reason")
    private String reversalReason;

    @Column(name = "reversed_at")
    private LocalDateTime reversedAt;

    @PrePersist
    public void generateTransactionId() {

        if (transactionId == null) {
            transactionId = "TXN-" +
                    UUID.randomUUID()
                            .toString()
                            .replace("-", "")
                            .substring(0, 12)
                            .toUpperCase();
        }

        if (status == null) {
            status = TransactionHistoryStatus.PENDING;
        }
    }
    @PreUpdate
    public void handleStatusChange(){
        if (status==TransactionHistoryStatus.REVERSED
        && reversedAt == null){
            reversedAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionHistoryStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionHistoryStatus status) {
        this.status = status;
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

    public String getReversalReason() {
        return reversalReason;
    }

    public void setReversalReason(String reversalReason) {
        this.reversalReason = reversalReason;
    }

    public LocalDateTime getReversedAt() {
        return reversedAt;
    }

    public void setReversedAt(LocalDateTime reversedAt) {
        this.reversedAt = reversedAt;
    }
}
