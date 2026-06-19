package com.ermias.bankingservice.bankingservice.repository;

import com.ermias.bankingservice.bankingservice.entity.TblApplication;
import com.ermias.bankingservice.bankingservice.model.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<TblApplication, Long> {
    Optional<TblApplication>findByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);
    Optional<TblApplication>findByAccountNumberAndStatus(String accountNumber, ApplicationStatus status);

}
