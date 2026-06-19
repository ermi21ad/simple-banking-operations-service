package com.ermias.bankingservice.bankingservice.service;

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
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final BankRepository bankRepository;
    private final BranchRepository branchRepository;

    public ApplicationService(ApplicationRepository applicationRepository, BankRepository bankRepository, BranchRepository branchRepository) {
        this.applicationRepository = applicationRepository;
        this.bankRepository = bankRepository;
        this.branchRepository = branchRepository;
    }

    @Transactional
    public ApplicationResponse submitApplication(
            ApplicationSubmitRequest request) {

        if (applicationRepository.existsByAccountNumber(
                request.getAccountNumber())) {

            throw new DuplicateAccountException(
                    "Account number already exists");
        }

        TblApplication application = new TblApplication();

        // Retrieve bank and branch first
        TblBank bank = bankRepository.findById(request.getBankId())
                .orElseThrow(() ->
                        new InvalidBankException("Bank not found"));

        TblBranch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() ->
                        new InvalidBranchException("Branch not found"));

        application.setBankName(bank.getValue());
        application.setBranchName(branch.getValue());

        application.setAccountName(request.getAccountName());
        application.setAccountNumber(request.getAccountNumber());
        application.setStatus(ApplicationStatus.SUBMITTED);

        TblApplication saved = applicationRepository.save(application);

        return ApplicationResponse.builder()
                .id(saved.getId())
                .accountNumber(saved.getAccountNumber())
                .accountName(saved.getAccountName())
                .status(saved.getStatus().name())
                .createdAt(saved.getCreatedAt())
                .message("Application submitted successfully")
                .build();
    }
    public boolean validateAccountStatus(
            String accountNumber) {

        return accountNumber != null
                && accountNumber.length() >= 10;
    }

}
