package com.inditex.product.validator;

import java.util.List;

public interface IProductRequestValidator {

  List<ValidationError> IsValidIdRequest(final Integer brandId, final String date, final Long productId);

}
