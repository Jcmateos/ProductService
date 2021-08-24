package com.inditex.product.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.inditex.product.exception.ProductServiceException;
import com.inditex.product.model.Product;
import com.inditex.product.repository.IProductRepository;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductServiceImpl productService;

	@Mock
	private IProductRepository productRepositoryMock;
	
	@Autowired
	private IProductRepository productRepository;
	
	@BeforeEach
	private void saveProducts() {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
				
		productRepository.save(new Product(1L,null,1, LocalDateTime.parse("2020-06-14T00:00:00", formatter),LocalDateTime.parse("2020-12-31T23:59:59", formatter),1,35455L,0,new BigDecimal("35.50"), Currency.getInstance("EUR")));
		productRepository.save(new Product(2L,null,1, LocalDateTime.parse("2020-06-14T15:00:00", formatter),LocalDateTime.parse("2020-06-14T18:30:00", formatter),2,35455L,1,new BigDecimal("25.45"), Currency.getInstance("EUR")));
		productRepository.save(new Product(3L,null,1, LocalDateTime.parse("2020-06-15T00:00:00", formatter),LocalDateTime.parse("2020-06-15T11:00:00", formatter),3,35455L,1,new BigDecimal("30.50"), Currency.getInstance("EUR")));
		productRepository.save(new Product(4L,null,1, LocalDateTime.parse("2020-06-15T16:00:00", formatter),LocalDateTime.parse("2020-12-31T23:59:59", formatter),4,35455L,1,new BigDecimal("38.95"), Currency.getInstance("EUR")));
	}
	
	@Test
	public void findProductsToApplyTest1() throws ProductServiceException {
		//Given
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		Integer brandId = 1;
		LocalDateTime date = LocalDateTime.parse("2020-06-14T10:00:00", formatter);
		Long productId = 35455L;
		
		List<Product> listProducts = productRepository.findAll();
		when(productRepositoryMock.findAll()).thenReturn(listProducts);
		
		//When
		Optional<Product> response = productService.findProductToApply(brandId, date, productId);
		
		//Then
		assertNotNull(response);
		assertEquals(new BigDecimal("35.50"),response.get().getPrice());
		verify(productRepositoryMock, times(1)).findAll();
	}
	
	@Test
	public void findProductsToApplyTest2() throws ProductServiceException {
		//Given
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		Integer brandId = 1;
		LocalDateTime date = LocalDateTime.parse("2020-06-14T16:00:00", formatter);
		Long productId = 35455L;
		
		List<Product> listProducts = productRepository.findAll();
		when(productRepositoryMock.findAll()).thenReturn(listProducts);
		
		//When
		Optional<Product> response = productService.findProductToApply(brandId, date, productId);
		
		//Then
		assertNotNull(response);
		assertEquals(new BigDecimal("25.45"),response.get().getPrice());
		verify(productRepositoryMock, times(1)).findAll();
	}
	
	@Test
	public void findProductsToApplyTest3() throws ProductServiceException {
		//Given
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		Integer brandId = 1;
		LocalDateTime date = LocalDateTime.parse("2020-06-14T21:00:00", formatter);
		Long productId = 35455L;
		
		List<Product> listProducts = productRepository.findAll();
		when(productRepositoryMock.findAll()).thenReturn(listProducts);
		
		//When
		Optional<Product> response = productService.findProductToApply(brandId, date, productId);
		
		//Then
		assertNotNull(response);
		assertEquals(new BigDecimal("25.45"),response.get().getPrice());
		verify(productRepositoryMock, times(1)).findAll();
	}
	
	@Test
	public void findProductsToApplyTest4() throws ProductServiceException {
		//Given
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		Integer brandId = 1;
		LocalDateTime date = LocalDateTime.parse("2020-06-15T10:00:00", formatter);
		Long productId = 35455L;
		
		List<Product> listProducts = productRepository.findAll();
		when(productRepositoryMock.findAll()).thenReturn(listProducts);
		
		//When
		Optional<Product> response = productService.findProductToApply(brandId, date, productId);
		
		//Then
		assertNotNull(response);
		assertEquals(new BigDecimal("30.50"),response.get().getPrice());
		verify(productRepositoryMock, times(1)).findAll();
	}
	
	@Test
	public void findProductsToApplyTest5() throws ProductServiceException {
		//Given
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		Integer brandId = 1;
		LocalDateTime date = LocalDateTime.parse("2020-06-16T21:00:00", formatter);
		Long productId = 35455L;
		
		List<Product> listProducts = productRepository.findAll();
		when(productRepositoryMock.findAll()).thenReturn(listProducts);
		
		//When
		Optional<Product> response = productService.findProductToApply(brandId, date, productId);
		
		//Then
		assertNotNull(response);
		assertEquals(new BigDecimal("38.95"),response.get().getPrice());
		verify(productRepositoryMock, times(1)).findAll();
	}

}
