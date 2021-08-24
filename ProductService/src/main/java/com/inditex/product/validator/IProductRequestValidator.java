package com.inditex.product.validator;

import java.util.List;

import com.inditex.product.model.dto.ProductValidationError;

public interface IProductRequestValidator {

	List<ProductValidationError> IsValidIdRequest (Integer brandId, String date, Long productId);
	
}
