package com.inditex.productService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inditex.productService.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{
	
}
