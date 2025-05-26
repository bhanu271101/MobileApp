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

    @ExceptionHandler(SessionTimeoutException.class)
    public ProblemDetail handleSessionTimeoutException(SessionTimeoutException sessionTimeoutException)
    {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401),sessionTimeoutException.getMessage());
    }

    @ExceptionHandler(PasswordValidationException.class)
    public ProblemDetail handlePasswordValidationException(PasswordValidationException passwordValidationException)
    {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400),passwordValidationException.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFoundException(UserNotFoundException userNotFoundException)
    {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), userNotFoundException.getMessage());
    }

}
