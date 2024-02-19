package com.ramibahrouni.ingredientsservice.exception;

import com.ramibahrouni.ingredientsservice.exception.IngredientException;
import com.ramibahrouni.ingredientsservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(IngredientException.class)
  public ResponseEntity<ErrorResponse> handleProductServiceException(IngredientException exception) {
    return new ResponseEntity<>(new ErrorResponse().builder()
        .errorMessage(exception.getMessage())
        .errorCode(exception.getErrorCode())
        .build(), HttpStatus.NOT_FOUND);
  }

}
