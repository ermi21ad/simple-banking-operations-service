package com.ermias.bankingservice.bankingservice.config;

import com.ermias.bankingservice.bankingservice.entity.TblBank;
import com.ermias.bankingservice.bankingservice.entity.TblBranch;
import com.ermias.bankingservice.bankingservice.repository.BankRepository;
import com.ermias.bankingservice.bankingservice.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final BankRepository bankRepository;
    private final BranchRepository branchRepository;

    @Override
    public void run(String... args) {

        if (bankRepository.count() > 0) {
            log.info("Sample data already exists. Skipping initialization.");
            return;
        }

        log.info("Initializing sample bank and branch data...");

        // ==========================
        // Commercial Bank of Ethiopia
        // ==========================
        TblBank cbe = new TblBank();
        cbe.setValue("Commercial Bank of Ethiopia");

        TblBank savedCbe = bankRepository.save(cbe);

        createBranch("Addis Ababa Main", savedCbe);
        createBranch("Bole Branch", savedCbe);
        createBranch("Merkato Branch", savedCbe);

        // ==========================
        // Awash Bank
        // ==========================
        TblBank awash = new TblBank();
        awash.setValue("Awash Bank");

        TblBank savedAwash = bankRepository.save(awash);

        createBranch("Piassa Branch", savedAwash);
        createBranch("Megenagna Branch", savedAwash);
        createBranch("CMC Branch", savedAwash);

        // ==========================
        // Dashen Bank
        // ==========================
        TblBank dashen = new TblBank();
        dashen.setValue("Dashen Bank");

        TblBank savedDashen = bankRepository.save(dashen);

        createBranch("Sarbet Branch", savedDashen);
        createBranch("Mexico Branch", savedDashen);
        createBranch("Kazanchis Branch", savedDashen);

        log.info("Bank and Branch sample data initialized successfully.");
    }

    private void createBranch(String branchName, TblBank bank) {

        TblBranch branch = new TblBranch();
        branch.setValue(branchName);
        branch.setBank(bank);

        branchRepository.save(branch);
    }
}