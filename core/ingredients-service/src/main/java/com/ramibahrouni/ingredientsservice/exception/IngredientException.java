package com.ramibahrouni.ingredientsservice.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class IngredientException extends RuntimeException{

  private String errorCode;

  public IngredientException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }
}
