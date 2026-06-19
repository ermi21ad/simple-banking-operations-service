package com.ermias.bankingservice.bankingservice.controller;

import com.ermias.bankingservice.bankingservice.dto.response.BranchResponse;
import com.ermias.bankingservice.bankingservice.service.BranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    public ResponseEntity<List<BranchResponse>> getBranchesByBankId(
            @RequestParam("bank_id") Long bankId) {

        return ResponseEntity.ok(
                branchService.getBranchesByBankId(bankId)
        );
    }
}