package com.inditex.product.exception;

public class ProductServiceException extends Exception {

	private static final long serialVersionUID = 8908380555199456980L;

	public ProductServiceException() {}
	
	public ProductServiceException(String message) {
		super(message);
	}
	
	public ProductServiceException(String message, Exception ex) {
		super(message, ex);
	}
}
