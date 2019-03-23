package com.sinners.error;

import com.sinners.exception.EmailSendingException;
import com.sinners.exception.InvalidEmailException;
import com.sinners.exception.PasswordMismatchException;
import com.sinners.exception.UserCreationException;
import com.sinners.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCode {
    BAD_EMAIL_CONNECTION(20, EmailSendingException.class, "Can't send email because of internal error"),
    INVALID_EMAIL(21, InvalidEmailException.class, "Invalid email"),
    DIFFERENT_PASSWORDS(30, PasswordMismatchException.class, "Passwords are different"),
    USER_NOT_FOUND(40, UserNotFoundException.class, "User not found"),
    USER_ALREADY_EXISTS(41, UserCreationException.class, "Can't create user"),
    ;

    public static final int UNHANDLED_EXCEPTION_CODE = 500;
    public static final String UNHANDLED_EXCEPTION_MESSAGE = "Server error";

    private int errorCode;
    private Class<? extends Exception> exception;
    private String message;
    private int httpStatusCode;

    ErrorCode(int errorCode, Class<? extends Exception> exception, String message) {
        this.errorCode = errorCode;
        this.exception = exception;
        this.message = message;
    }

    private static final Map<String, ErrorCode> map = new HashMap<>();

    static {
        for (ErrorCode code : ErrorCode.values()) {
            String key = code.getException().getName();
            if (map.get(key) != null) {
                throw new InitializationException("Two different values with the same map key");
            }
            map.put(key, code);
        }
    }

    public static ErrorCode getCodeForException(Exception e) {
        return map.get(e.getClass().getName());
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Class<? extends Exception> getException() {
        return exception;
    }

    public void setException(Class<? extends Exception> exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
