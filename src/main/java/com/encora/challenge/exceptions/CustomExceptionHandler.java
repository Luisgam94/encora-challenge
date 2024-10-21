package com.encora.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(CustomizedMessageException.class)
  public ResponseEntity<ErrorResponse> handleCustomException(CustomizedMessageException ex) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setTimestamp(LocalDateTime.now());
    errorResponse.setDetails(details);

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

}

