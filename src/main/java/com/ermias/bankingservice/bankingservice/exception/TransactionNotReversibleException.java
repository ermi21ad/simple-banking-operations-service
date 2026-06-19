package com.ermias.bankingservice.bankingservice.exception;

public class TransactionNotReversibleException extends RuntimeException {

    public TransactionNotReversibleException(String message) {
        super(message);
    }
}