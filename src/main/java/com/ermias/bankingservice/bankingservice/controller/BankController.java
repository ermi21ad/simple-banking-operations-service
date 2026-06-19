package com.ermias.bankingservice.bankingservice.controller;

import com.ermias.bankingservice.bankingservice.dto.response.BankResponse;
import com.ermias.bankingservice.bankingservice.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public ResponseEntity<List<BankResponse>> getAllBanks() {

        return ResponseEntity.ok(
                bankService.getAllBanks()
        );
    }
}