package com.example.memo.Exception;

public class UserNameAlreadyExistsException extends RuntimeException{

    public UserNameAlreadyExistsException(String message)
    {
        super(message);
    }

}
