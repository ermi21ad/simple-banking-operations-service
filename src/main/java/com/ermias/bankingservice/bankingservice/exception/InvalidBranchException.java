package com.ermias.bankingservice.bankingservice.exception;

public class InvalidBranchException extends RuntimeException {

    public InvalidBranchException(String message) {
        super(message);
    }
}