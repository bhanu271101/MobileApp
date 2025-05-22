package com.example.memo.Exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ProblemDetail handleUserNameAlreadyExistsException(UserNameAlreadyExistsException ex)
    {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400),ex.getMessage());
    }

    @ExceptionHandler(PasswordNotMatchingException.class)
    public ProblemDetail handlePasswordNotMatchingException(PasswordNotMatchingException ex)
    {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getMessage());
    }

    @ExceptionHandler(PhoneNumberValidationException.class)
    public ProblemDetail handlePhoneNumberValidationException(PhoneNumberValidationException ex)
    {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400),ex.getMessage());
    }

    public ProblemDetail handleSessionTimeoutException(SessionTimeoutException sessionTimeoutException)
    {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401),sessionTimeoutException.getMessage());
    }

}
