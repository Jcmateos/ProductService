package com.inditex.product.validator;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ValidationError {

  String field;
  ValidationErrorMessage message;

}
