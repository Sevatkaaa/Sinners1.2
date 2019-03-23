package com.sinners.exception;

public class EmailSendingException extends RuntimeException {
    public EmailSendingException(Exception e) {
        super(e);
    }
}
