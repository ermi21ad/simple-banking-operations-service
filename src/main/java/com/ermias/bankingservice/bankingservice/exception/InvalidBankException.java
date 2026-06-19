package com.ermias.bankingservice.bankingservice.exception;

public class InvalidBankException extends RuntimeException {

    public InvalidBankException(String message) {
        super(message);
    }
}