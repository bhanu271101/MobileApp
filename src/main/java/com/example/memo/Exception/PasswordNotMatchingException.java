package com.example.memo.Exception;

public class PasswordNotMatchingException extends RuntimeException{
    
    public PasswordNotMatchingException(String message)
    {
        super(message);
    }

}
