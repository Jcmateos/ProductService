package com.inditex.productService.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.productService.exception.ProductServiceException;
import com.inditex.productService.model.Product;

public interface IProductService {

	public Optional<Product> findProductToApply(Integer brandId, LocalDateTime date, Long productId) throws ProductServiceException;
		
}
