package com.ermias.bankingservice.bankingservice.repository;

import com.ermias.bankingservice.bankingservice.entity.TblBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BankRepository extends JpaRepository<TblBank, Long> {
    List<TblBank> findAllByOrderByValueAsc();
}
