package com.inditex.product.service.impl;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inditex.product.exception.ProductServiceException;
import com.inditex.product.model.Product;
import com.inditex.product.repository.IProductRepository;
import com.inditex.product.service.IProductService;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	public Optional<Product> findProductToApply(Integer brandId, LocalDateTime date, Long productId) throws ProductServiceException {
		
		List<Product> listPrducts = productRepository.findAll();
		
		return listPrducts.stream()
				.filter(p -> p.getProductId().equals(productId) && p.getBrandId().equals(brandId) && (p.getStartDate().isBefore(date)))
				.sorted(Comparator.comparing(Product::getStartDate).reversed())
				.max(Comparator.comparing(Product::getPriority));
	}

}
