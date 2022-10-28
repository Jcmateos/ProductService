package com.inditex.product.validator;

import static com.inditex.product.validator.ValidationErrorMessage.THE_FIELD_BRAND_ID_IS_REQUIRED;
import static com.inditex.product.validator.ValidationErrorMessage.THE_FIELD_DATE_IS_REQUIRED;
import static com.inditex.product.validator.ValidationErrorMessage.THE_FIELD_PRODUCT_ID_IS_REQUIRED;
import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductRequestValidator implements IProductRequestValidator {

  @Override
  public List<ValidationError> IsValidIdRequest(final Integer brandId, final String date, final Long productId) {
    List<ValidationError> validationErrors = new ArrayList<>();

    if (isNull(brandId)) {
      validationErrors.add(new ValidationError("brandId", THE_FIELD_BRAND_ID_IS_REQUIRED));
    }
    if (isNull(date)) {
      validationErrors.add(new ValidationError("date", THE_FIELD_DATE_IS_REQUIRED));
    }
    if (isNull(productId)) {
      validationErrors.add(new ValidationError("productId", THE_FIELD_PRODUCT_ID_IS_REQUIRED));
    }

    return validationErrors;
  }

}
