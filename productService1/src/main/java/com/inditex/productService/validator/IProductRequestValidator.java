package com.inditex.productService.validator;

import java.time.OffsetDateTime;
import java.util.List;

import com.inditex.productService.dto.ProductValidationError;

public interface IProductRequestValidator {

	List<ProductValidationError> IsValidIdRequest (Integer brandId, OffsetDateTime date, Long productId);
	
}
