package com.ermias.bankingservice.bankingservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponse {
    private String transactionId;

    private String status;

    private String message;

    private LocalDateTime timestamp;
}
