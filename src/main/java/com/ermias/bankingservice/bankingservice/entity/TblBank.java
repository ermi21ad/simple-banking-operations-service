package com.ermias.bankingservice.bankingservice.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_bank")
public class TblBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String value;

    @OneToMany(
            mappedBy = "bank",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<TblBranch> branches = new ArrayList<>();

    // Constructors
    public TblBank() {}

    public TblBank(String value) {
        this.value = value;
    }

    // Getters and Setters
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

    public List<TblBranch> getBranches() {
        return branches;
    }

    public void setBranches(List<TblBranch> branches) {
        this.branches = branches;
    }

    // Helper methods for managing bi-directional relationship
    public void addBranch(TblBranch branch) {
        if (branches == null) {
            branches = new ArrayList<>();
        }
        branches.add(branch);
        branch.setBank(this);
    }

    public void removeBranch(TblBranch branch) {
        branches.remove(branch);
        branch.setBank(null);
    }
}