package com.inditex.product.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditex.product.exception.ProductServiceException;
import com.inditex.product.model.Product;

public interface IProductService {

	public Optional<Product> findProductToApply(Integer brandId, LocalDateTime date, Long productId) throws ProductServiceException;
		
}
