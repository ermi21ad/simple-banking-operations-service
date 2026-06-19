package com.ermias.bankingservice.bankingservice.repository;

import com.ermias.bankingservice.bankingservice.entity.TblTransactionHistory;
import com.ermias.bankingservice.bankingservice.model.enums.ApplicationStatus;
import com.ermias.bankingservice.bankingservice.model.enums.TransactionHistoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionHistoryRepository extends JpaRepository<TblTransactionHistory, Long> {
    Optional<TblTransactionHistory> findByTransactionId(String transactionId);
    Optional<TblTransactionHistory> findByTransactionIdAndStatus(String transactionId, TransactionHistoryStatus status);
    boolean existsByTransactionId(String transactionId);
    Optional<TblTransactionHistory> findByTransactionIdAndStatusNot(String transactionId, TransactionHistoryStatus status);
}
