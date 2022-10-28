package com.inditex.product.exception;

import lombok.Getter;

public class ProductServiceException extends RuntimeException {

  @Getter
  private final ProductExceptionType type;

  protected ProductServiceException(final String message, final ProductExceptionType type) {
    super(message);
    this.type = type;
  }

}
