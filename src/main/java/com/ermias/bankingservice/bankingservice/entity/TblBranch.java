package com.ermias.bankingservice.bankingservice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_branch")
public class TblBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private TblBank bank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TblBank getBank() {
        return bank;
    }

    public void setBank(TblBank bank) {
        this.bank = bank;
    }
}
