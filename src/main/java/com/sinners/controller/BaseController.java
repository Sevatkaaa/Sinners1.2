package com.sinners.controller;

import com.sinners.error.ErrorCode;
import com.sinners.error.ErrorData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class BaseController {

    private static final String TIMESTAMP_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final String NO_INFO_MESSAGE = "no info";

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorData> handleException(Exception e) {
        ErrorCode errorCode = ErrorCode.getCodeForException(e);

        return errorCode == null ? getServerErrorResponse(e) : getErrorResponseForCode(errorCode, e);
    }

    private ResponseEntity<ErrorData> getErrorResponseForCode(ErrorCode errorCode, Exception e) {
        ErrorData errorData = new ErrorData();
        errorData.setErrorCode(errorCode.getErrorCode());
        errorData.setMessage(errorCode.getMessage());
        String originalMessage = e.getMessage();
        errorData.setOriginalMessage(originalMessage == null ? NO_INFO_MESSAGE : originalMessage);
        errorData.setTimestamp(getTimestamp());
        return new ResponseEntity<>(errorData, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorData> getServerErrorResponse(Exception e) {
        ErrorData errorData = new ErrorData();
        errorData.setErrorCode(ErrorCode.UNHANDLED_EXCEPTION_CODE);
        errorData.setMessage(ErrorCode.UNHANDLED_EXCEPTION_MESSAGE);
        errorData.setOriginalMessage(e.getMessage());
        errorData.setTimestamp(getTimestamp());
        return new ResponseEntity<>(errorData, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT));
    }
}
