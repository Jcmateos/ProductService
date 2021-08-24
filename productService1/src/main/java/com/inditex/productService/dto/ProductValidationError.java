package com.inditex.productService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductValidationError {

	public enum ErrorCode {
		THE_FIELD_ID_IS_REQUIRED
	}
	
	public ErrorCode errorCode;
}
