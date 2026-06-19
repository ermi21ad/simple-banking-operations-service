package com.ermias.bankingservice.bankingservice.service;

import com.ermias.bankingservice.bankingservice.dto.response.BankResponse;
import com.ermias.bankingservice.bankingservice.entity.TblBank;
import com.ermias.bankingservice.bankingservice.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService {

    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<BankResponse>getAllBanks(){
        return bankRepository.findAllByOrderByValueAsc()
                .stream()
                .map(tblBank -> BankResponse.builder()
                        .id(tblBank.getId())
                        .value(tblBank.getValue())
                        .build())
                .collect(Collectors.toList());
    }

}
