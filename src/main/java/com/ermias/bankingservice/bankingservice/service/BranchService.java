package com.ermias.bankingservice.bankingservice.service;
import com.ermias.bankingservice.bankingservice.dto.response.BranchResponse;
import com.ermias.bankingservice.bankingservice.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<BranchResponse>getBranchesByBankId(Long bankId) {
        return branchRepository.findByBankIdOrderByValueAsc(bankId)
                .stream()
                .map(tblBranch -> BranchResponse.builder()
                        .id(tblBranch.getId())
                        .value(tblBranch.getValue())
                        .bankId(tblBranch.getBank().getId())
                        .build())
                .collect(Collectors.toList());

    }




}
