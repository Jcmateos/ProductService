package com.inditex.product.exception;

public class UnknownDatabaseError extends ProductServiceException {

  public UnknownDatabaseError(final ProductExceptionType applicationExceptionType, final String message) {
    super(message, applicationExceptionType);
  }

}
