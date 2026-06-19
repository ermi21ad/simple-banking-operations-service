package com.ermias.bankingservice.bankingservice.service.impl;

import com.ermias.bankingservice.bankingservice.dto.request.ApplicationSubmitRequest;
import com.ermias.bankingservice.bankingservice.dto.response.ApplicationResponse;
import com.ermias.bankingservice.bankingservice.entity.TblApplication;
import com.ermias.bankingservice.bankingservice.entity.TblBank;
import com.ermias.bankingservice.bankingservice.entity.TblBranch;
import com.ermias.bankingservice.bankingservice.exception.DuplicateAccountException;
import com.ermias.bankingservice.bankingservice.exception.InvalidBankException;
import com.ermias.bankingservice.bankingservice.exception.InvalidBranchException;
import com.ermias.bankingservice.bankingservice.model.enums.ApplicationStatus;
import com.ermias.bankingservice.bankingservice.repository.ApplicationRepository;
import com.ermias.bankingservice.bankingservice.repository.BankRepository;
import com.ermias.bankingservice.bankingservice.repository.BranchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ApplicationServiceImpl {

    private final ApplicationRepository applicationRepository;
    private final BankRepository bankRepository;
    private final BranchRepository branchRepository;

    public ApplicationServiceImpl(
            ApplicationRepository applicationRepository,
            BankRepository bankRepository,
            BranchRepository branchRepository) {

        this.applicationRepository = applicationRepository;
        this.bankRepository = bankRepository;
        this.branchRepository = branchRepository;
    }

    @Transactional
    public ApplicationResponse submitApplication(
            ApplicationSubmitRequest request) {

        log.info("Submitting application for account number {}",
                request.getAccountNumber());

        if (applicationRepository.existsByAccountNumber(
                request.getAccountNumber())) {

            throw new DuplicateAccountException(
                    "Account number already exists");
        }

        TblBank bank = bankRepository.findById(request.getBankId())
                .orElseThrow(() ->
                        new InvalidBankException("Bank not found"));

        TblBranch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() ->
                        new InvalidBranchException("Branch not found"));

        TblApplication application = new TblApplication();

        application.setBankName(bank.getValue());
        application.setBranchName(branch.getValue());
        application.setAccountName(request.getAccountName());
        application.setAccountNumber(request.getAccountNumber());
        application.setStatus(ApplicationStatus.SUBMITTED);

        TblApplication saved =
                applicationRepository.save(application);

        log.info("Application submitted successfully. ID={}",
                saved.getId());

        return ApplicationResponse.builder()
                .id(saved.getId())
                .accountNumber(saved.getAccountNumber())
                .accountName(saved.getAccountName())
                .status(saved.getStatus().name())
                .createdAt(saved.getCreatedAt())
                .message("Application submitted successfully")
                .build();
    }
}