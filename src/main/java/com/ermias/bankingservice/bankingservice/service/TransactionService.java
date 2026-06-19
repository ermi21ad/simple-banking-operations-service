package com.ermias.bankingservice.bankingservice.service;

import com.ermias.bankingservice.bankingservice.dto.request.ReversalRequest;
import com.ermias.bankingservice.bankingservice.dto.request.TransactionRequest;
import com.ermias.bankingservice.bankingservice.dto.response.TransactionResponse;
import com.ermias.bankingservice.bankingservice.entity.TblTransactionHistory;
import com.ermias.bankingservice.bankingservice.model.enums.TransactionHistoryStatus;
import com.ermias.bankingservice.bankingservice.repository.ApplicationRepository;
import com.ermias.bankingservice.bankingservice.repository.TransactionHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionHistoryRepository transactionHistoryRepository;
    private final ApplicationRepository applicationRepository;

    public TransactionService(
            TransactionHistoryRepository transactionHistoryRepository,
            ApplicationRepository applicationRepository) {

        this.transactionHistoryRepository = transactionHistoryRepository;
        this.applicationRepository = applicationRepository;
    }

    public TransactionResponse processTransaction(TransactionRequest request) {

        validateAccount(request.getAccountNumber());

        TblTransactionHistory transaction = new TblTransactionHistory();

        transaction.setAccountNumber(request.getAccountNumber());
        transaction.setAmount(request.getAmount());
        transaction.setNarration(request.getNarration());

        // value can be description or narration
        transaction.setValue(request.getNarration());

        updateStatus(transaction, TransactionHistoryStatus.SUCCESS);

        TblTransactionHistory saved =
                transactionHistoryRepository.save(transaction);

        return TransactionResponse.builder()
                .transactionId(saved.getTransactionId())
                .status(saved.getStatus().name())
                .message("Transaction processed successfully")
                .timestamp(saved.getCreatedAt())
                .build();
    }

    public TransactionResponse reverseTransaction(ReversalRequest request) {

        TblTransactionHistory transaction =
                transactionHistoryRepository
                        .findByTransactionId(request.getTransactionId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Transaction not found"));

        validateReversal(transaction);

        transaction.setReversalReason(request.getReason());

        transaction.setReversedAt(LocalDateTime.now());

        updateStatus(
                transaction,
                TransactionHistoryStatus.REVERSED);

        TblTransactionHistory saved =
                transactionHistoryRepository.save(transaction);

        return TransactionResponse.builder()
                .transactionId(saved.getTransactionId())
                .status(saved.getStatus().name())
                .message("Transaction reversed successfully")
                .timestamp(LocalDateTime.now())
                .build();
    }

    private void validateAccount(String accountNumber) {

        boolean exists =
                applicationRepository
                        .existsByAccountNumber(accountNumber);

        if (!exists) {
            throw new RuntimeException(
                    "Account number not found: " + accountNumber);
        }
    }

    private void validateReversal(
            TblTransactionHistory transaction) {

        if (transaction.getStatus()
                == TransactionHistoryStatus.REVERSED) {

            throw new RuntimeException(
                    "Transaction has already been reversed");
        }
    }

    private void updateStatus(
            TblTransactionHistory transaction,
            TransactionHistoryStatus status) {

        transaction.setStatus(status);
    }
}