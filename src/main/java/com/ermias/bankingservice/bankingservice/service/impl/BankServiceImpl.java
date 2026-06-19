package com.ermias.bankingservice.bankingservice.service.impl;

import com.ermias.bankingservice.bankingservice.dto.response.BankResponse;
import com.ermias.bankingservice.bankingservice.entity.TblBank;
import com.ermias.bankingservice.bankingservice.exception.ResourceNotFoundException;
import com.ermias.bankingservice.bankingservice.repository.BankRepository;
import com.ermias.bankingservice.bankingservice.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BankServiceImpl {

    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }


    public List<BankResponse> getAllBanks() {

        log.info("Fetching all banks");

        List<TblBank> banks =
                bankRepository.findAllByOrderByValueAsc();

        if (banks.isEmpty()) {

            log.error("No banks found");

            throw new ResourceNotFoundException(
                    "No banks found");
        }

        List<BankResponse> responses =
                banks.stream()
                        .map(bank -> BankResponse.builder()
                                .id(bank.getId())
                                .value(bank.getValue())
                                .build())
                        .collect(Collectors.toList());

        log.info("Successfully fetched {} banks",
                responses.size());

        return responses;
    }
}