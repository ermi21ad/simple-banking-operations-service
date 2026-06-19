package com.ermias.bankingservice.bankingservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApplicationResponse {
    private Long id;

    private String accountNumber;

    private String accountName;

    private String status;

    private LocalDateTime createdAt;

    private String message;
}
