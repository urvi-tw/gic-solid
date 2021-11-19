package com.tw.gic.bootcamp.solid.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private final HttpStatus status;
    private final String message;

}
