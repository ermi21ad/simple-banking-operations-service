package com.ermias.bankingservice.bankingservice.service.impl;

import com.ermias.bankingservice.bankingservice.dto.request.ReversalRequest;
import com.ermias.bankingservice.bankingservice.dto.request.TransactionRequest;
import com.ermias.bankingservice.bankingservice.dto.response.TransactionResponse;
import com.ermias.bankingservice.bankingservice.entity.TblApplication;
import com.ermias.bankingservice.bankingservice.entity.TblTransactionHistory;
import com.ermias.bankingservice.bankingservice.exception.TransactionNotReversibleException;
import com.ermias.bankingservice.bankingservice.model.enums.ApplicationStatus;
import com.ermias.bankingservice.bankingservice.model.enums.TransactionHistoryStatus;
import com.ermias.bankingservice.bankingservice.repository.ApplicationRepository;
import com.ermias.bankingservice.bankingservice.repository.TransactionHistoryRepository;
import com.ermias.bankingservice.bankingservice.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class TransactionServiceImpl{

    private final ApplicationRepository applicationRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    public TransactionServiceImpl(
            ApplicationRepository applicationRepository,
            TransactionHistoryRepository transactionHistoryRepository) {

        this.applicationRepository =
                applicationRepository;

        this.transactionHistoryRepository =
                transactionHistoryRepository;
    }


    @Transactional
    public TransactionResponse processTransaction(
            TransactionRequest request) {

        log.info(
                "Processing transaction for account {}",
                request.getAccountNumber());

        TblTransactionHistory transaction =
                new TblTransactionHistory();

        transaction.setAccountNumber(
                request.getAccountNumber());

        transaction.setAmount(
                request.getAmount());

        transaction.setNarration(
                request.getNarration());

        transaction.setValue(
                request.getNarration());

        TblApplication application =
                applicationRepository
                        .findByAccountNumber(
                                request.getAccountNumber())
                        .orElse(null);

        if (application == null ||
                application.getStatus()
                        != ApplicationStatus.SUBMITTED) {

            log.warn(
                    "Account validation failed for {}",
                    request.getAccountNumber());

            transaction.setStatus(
                    TransactionHistoryStatus.FAILED);

            TblTransactionHistory saved =
                    transactionHistoryRepository
                            .save(transaction);

            return TransactionResponse.builder()
                    .transactionId(
                            saved.getTransactionId())
                    .status(
                            saved.getStatus().name())
                    .message(
                            "Transaction failed. Invalid account or status.")
                    .timestamp(
                            LocalDateTime.now())
                    .build();
        }

        transaction.setStatus(
                TransactionHistoryStatus.SUCCESS);

        TblTransactionHistory saved =
                transactionHistoryRepository
                        .save(transaction);

        log.info(
                "Transaction successful. ID={}",
                saved.getTransactionId());

        return TransactionResponse.builder()
                .transactionId(
                        saved.getTransactionId())
                .status(
                        saved.getStatus().name())
                .message(
                        "Transaction processed successfully")
                .timestamp(
                        LocalDateTime.now())
                .build();
    }


    @Transactional
    public TransactionResponse reverseTransaction(
            ReversalRequest request) {

        log.info(
                "Reversal request for transaction {}",
                request.getTransactionId());

        TblTransactionHistory transaction =
                transactionHistoryRepository
                        .findByTransactionId(
                                request.getTransactionId())
                        .orElseThrow(() ->
                                new TransactionNotReversibleException(
                                        "Transaction not found"));

        if (transaction.getStatus()
                == TransactionHistoryStatus.REVERSED) {

            throw new TransactionNotReversibleException(
                    "Transaction already reversed");
        }

        if (transaction.getStatus()
                != TransactionHistoryStatus.SUCCESS
                &&
                transaction.getStatus()
                        != TransactionHistoryStatus.FAILED) {

            throw new TransactionNotReversibleException(
                    "Transaction cannot be reversed");
        }

        transaction.setStatus(
                TransactionHistoryStatus.REVERSED);

        transaction.setReversalReason(
                request.getReason());

        transaction.setReversedAt(
                LocalDateTime.now());

        TblTransactionHistory saved =
                transactionHistoryRepository
                        .save(transaction);

        log.info(
                "Transaction reversed successfully {}",
                saved.getTransactionId());

        return TransactionResponse.builder()
                .transactionId(
                        saved.getTransactionId())
                .status(
                        saved.getStatus().name())
                .message(
                        "Transaction reversed successfully")
                .timestamp(
                        LocalDateTime.now())
                .build();
    }
}