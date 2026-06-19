package com.ermias.bankingservice.bankingservice.repository;

import com.ermias.bankingservice.bankingservice.entity.TblBranch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<TblBranch, Long> {
    List<TblBranch> findByBankIdOrderByValueAsc(Long bankId);
}
