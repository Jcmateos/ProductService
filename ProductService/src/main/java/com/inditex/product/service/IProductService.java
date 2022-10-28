package com.inditex.product.service;

import com.inditex.product.exception.ProductServiceException;
import com.inditex.product.model.Product;
import java.time.LocalDateTime;
import java.util.Optional;

public interface IProductService {

  Optional<Product> findProductToApply(final Integer brandId, final LocalDateTime date, final Long productId) throws ProductServiceException;

}
