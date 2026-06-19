package com.ermias.bankingservice.bankingservice.exception;

public class BusinessValidationException extends RuntimeException
{
    public BusinessValidationException(String message)
    {
        super(message);
    }
}
