package com.example.memo.Exception;

public class SessionTimeoutException extends RuntimeException{

    public SessionTimeoutException(String message)
    {
        super(message);
    }

}
