package com.inditex.productService.validator;

import java.util.List;

import com.inditex.productService.model.dto.ProductValidationError;

public interface IProductRequestValidator {

	List<ProductValidationError> IsValidIdRequest (Integer brandId, String date, Long productId);
	
}
