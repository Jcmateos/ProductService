package com.inditex.productService.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inditex.productService.exception.ProductServiceException;
import com.inditex.productService.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{

	public Optional<Product> findProductToApply(Integer brandId, OffsetDateTime date, Long productId) throws ProductServiceException;
	
}
