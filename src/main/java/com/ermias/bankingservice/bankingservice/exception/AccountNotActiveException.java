package com.ermias.bankingservice.bankingservice.exception;

public class AccountNotActiveException extends RuntimeException {

    public AccountNotActiveException(String message) {
        super(message);
    }
}