package com.inditex.productService.service;

import java.time.OffsetDateTime;
import java.util.Optional;

import com.inditex.productService.exception.ProductServiceException;
import com.inditex.productService.model.Product;

public interface IProductService {

	public Optional<Product> findProductToApply(Integer brandId, OffsetDateTime date, Long productId) throws ProductServiceException;
		
}
