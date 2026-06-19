package com.ermias.bankingservice.bankingservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankResponse {
    private Long id;
    private String value;
}
