package com.sinners.controller;

import com.sinners.error.ErrorCode;
import com.sinners.error.ErrorData;
import com.sinners.exception.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BaseControllerUnitTest {

    public static final String MESSAGE = "message";
    private BaseController controller;

    @Before
    public void setUp() {
        controller = new BaseController();
    }

    @Test
    public void shouldHandleExceptionWithExistingCode() {
        ResponseEntity<ErrorData> actual = controller.handleException(new UserNotFoundException(MESSAGE));

        ErrorData actualData = actual.getBody();
        assertThat(actualData.getErrorCode()).isEqualTo(40);
        assertThat(actualData.getMessage()).isEqualTo("User not found");
        assertThat(actualData.getOriginalMessage()).isEqualTo(MESSAGE);
        assertThat(actualData.getTimestamp()).isNotEmpty();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldHandleExceptionWithServerErrorCode() {
        ResponseEntity<ErrorData> actual = controller.handleException(new Exception(MESSAGE));

        ErrorData actualData = actual.getBody();
        assertThat(actualData.getErrorCode()).isEqualTo(ErrorCode.UNHANDLED_EXCEPTION_CODE);
        assertThat(actualData.getMessage()).isEqualTo(ErrorCode.UNHANDLED_EXCEPTION_MESSAGE);
        assertThat(actualData.getOriginalMessage()).isEqualTo(MESSAGE);
        assertThat(actualData.getTimestamp()).isNotEmpty();
        assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
