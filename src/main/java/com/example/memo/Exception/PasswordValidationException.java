package com.example.memo.Exception;

public class PasswordValidationException extends RuntimeException {
    public PasswordValidationException(String message)
    {
        super(message);
    }

}
