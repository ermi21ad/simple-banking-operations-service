package com.ermias.bankingservice.bankingservice.controller;

import com.ermias.bankingservice.bankingservice.dto.request.ReversalRequest;
import com.ermias.bankingservice.bankingservice.dto.request.TransactionRequest;
import com.ermias.bankingservice.bankingservice.dto.response.TransactionResponse;
import com.ermias.bankingservice.bankingservice.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionResponse>processTransaction(@Valid @RequestBody TransactionRequest request) {
        TransactionResponse response=
                transactionService.processTransaction(request);
        if("SUCCESS".equals(response.getStatus())){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
    @PostMapping("/reverse")
    public ResponseEntity<TransactionResponse> reverseTransaction(
            @Valid @RequestBody ReversalRequest request) {

        TransactionResponse response =
                transactionService.reverseTransaction(request);

        return ResponseEntity.ok(response);
    }
}