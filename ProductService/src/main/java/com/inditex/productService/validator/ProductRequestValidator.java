package com.inditex.productService.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inditex.productService.model.dto.ProductValidationError;
import com.inditex.productService.model.dto.ProductValidationError.ErrorCode;

@Service
public class ProductRequestValidator implements IProductRequestValidator {

	@Override
	public List<ProductValidationError> IsValidIdRequest(Integer brandId, String date, Long productId) {
		List<ProductValidationError> validationErrors = new ArrayList<ProductValidationError>();
		
		if (brandId == null || date == null || productId == null)
			validationErrors.add(new ProductValidationError(ErrorCode.THE_FIELD_ID_IS_REQUIRED));
		
		return validationErrors;
	}

}
