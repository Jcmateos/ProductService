package com.inditex.productService.service.impl;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.productService.exception.ProductServiceException;
import com.inditex.productService.model.Product;
import com.inditex.productService.repository.IProductRepository;
import com.inditex.productService.service.IProductService;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {
		
	@Autowired
	private IProductRepository productRepository;

	@Override
	public Optional<Product> findProductToApply(Integer brandId, OffsetDateTime date, Long productId) throws ProductServiceException {
		return productRepository.findProductToApply(brandId, date, productId);
	}


}
