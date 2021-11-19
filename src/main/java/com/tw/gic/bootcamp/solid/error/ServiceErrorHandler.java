package com.tw.gic.bootcamp.solid.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ServiceErrorHandler {

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<String> handlServiceException(Exception ex) {
        ErrorResponse apiError = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @ExceptionHandler({InterruptedException.class})
    public ResponseEntity<String> handleInterruptedException(Exception ex) {
        ErrorResponse apiError = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage() + ". Try again");
        return new ResponseEntity(apiError, apiError.getStatus());
    }
}
