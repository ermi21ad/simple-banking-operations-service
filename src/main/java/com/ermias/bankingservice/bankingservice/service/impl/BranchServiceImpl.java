package com.ermias.bankingservice.bankingservice.service.impl;

import com.ermias.bankingservice.bankingservice.dto.response.BranchResponse;
import com.ermias.bankingservice.bankingservice.entity.TblBank;
import com.ermias.bankingservice.bankingservice.entity.TblBranch;
import com.ermias.bankingservice.bankingservice.exception.ResourceNotFoundException;
import com.ermias.bankingservice.bankingservice.repository.BankRepository;
import com.ermias.bankingservice.bankingservice.repository.BranchRepository;
import com.ermias.bankingservice.bankingservice.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BranchServiceImpl {

    private final BranchRepository branchRepository;
    private final BankRepository bankRepository;

    public BranchServiceImpl(
            BranchRepository branchRepository,
            BankRepository bankRepository) {

        this.branchRepository = branchRepository;
        this.bankRepository = bankRepository;
    }


    public List<BranchResponse> getBranchesByBankId(Long bankId) {

        log.info("Fetching branches for bank id: {}", bankId);

        TblBank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> {

                    log.error(
                            "Bank not found with id: {}",
                            bankId);

                    return new ResourceNotFoundException(
                            "Bank not found with id: "
                                    + bankId);
                });

        List<TblBranch> branches =
                branchRepository
                        .findByBankIdOrderByValueAsc(bankId);

        if (branches.isEmpty()) {

            log.error(
                    "No branches found for bank id: {}",
                    bankId);

            throw new ResourceNotFoundException(
                    "No branches found for bank id: "
                            + bankId);
        }

        List<BranchResponse> responses =
                branches.stream()
                        .map(branch ->
                                BranchResponse.builder()
                                        .id(branch.getId())
                                        .value(branch.getValue())
                                        .bankId(bank.getId())
                                        .build())
                        .collect(Collectors.toList());

        log.info(
                "Successfully fetched {} branches for bank id {}",
                responses.size(),
                bankId);

        return responses;
    }
}