package com.inditex.product.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.inditex.product.exception.ProductServiceException;
import com.inditex.product.model.Product;
import com.inditex.product.model.dto.ProductResponse;
import com.inditex.product.model.dto.ProductValidationError;
import com.inditex.product.repository.IProductRepository;
import com.inditex.product.service.IProductService;
import com.inditex.product.validator.IProductRequestValidator;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

	@InjectMocks
	private ProductController productController;
	
	@Mock
	private IProductService productService;
	
	@Mock
	private IProductRequestValidator productRequestValidator;
	
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
	public void getProductToApplyTest1() throws ProductServiceException {
		//Given
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		Integer brandId = 1;
		String date = "2020-06-14T10:00:00";
		Long productId = 35455L;
		
		Optional<Product> product = productRepository.findById(1L);
		when(productRequestValidator.IsValidIdRequest(brandId, date, productId)).thenReturn(new ArrayList<ProductValidationError>());
		when(productService.findProductToApply(brandId, LocalDateTime.parse(date, formatter), productId)).thenReturn(product);
		
		//When
		ResponseEntity<?> response = productController.getProductToApply(brandId, date, productId);
		
		//Then
		assertNotNull(response);
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(new BigDecimal("35.50"),((ProductResponse) response.getBody()).getPrice());
		verify(productService, times(1)).findProductToApply(brandId, LocalDateTime.parse(date, formatter), productId);
		verify(productRequestValidator, times(1)).IsValidIdRequest(brandId, date, productId);
	}
	
	@Test
	public void getProductToApplyTest2() throws ProductServiceException {
		//Given
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		Integer brandId = 1;
		String date = "2020-06-14T16:00:00";
		Long productId = 35455L;
		
		Optional<Product> product = productRepository.findById(2L);
		when(productRequestValidator.IsValidIdRequest(brandId, date, productId)).thenReturn(new ArrayList<ProductValidationError>());
		when(productService.findProductToApply(brandId, LocalDateTime.parse(date, formatter), productId)).thenReturn(product);
		
		//When
		ResponseEntity<?> response = productController.getProductToApply(brandId, date, productId);
		
		//Then
		assertNotNull(response);
		assertEquals(new BigDecimal("25.45"),((ProductResponse) response.getBody()).getPrice());
		verify(productService, times(1)).findProductToApply(brandId, LocalDateTime.parse(date, formatter), productId);
		verify(productRequestValidator, times(1)).IsValidIdRequest(brandId, date, productId);
	}
	
	@Test
	public void getProductToApplyTest3() throws ProductServiceException {
		//Given
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		Integer brandId = 1;
		String date = "2020-06-14T21:00:00";
		Long productId = 35455L;
		
		Optional<Product> product = productRepository.findById(2L);
		when(productRequestValidator.IsValidIdRequest(brandId, date, productId)).thenReturn(new ArrayList<ProductValidationError>());
		when(productService.findProductToApply(brandId, LocalDateTime.parse(date, formatter), productId)).thenReturn(product);
		
		//When
		ResponseEntity<?> response = productController.getProductToApply(brandId, date, productId);
		
		//Then
		assertNotNull(response);
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(new BigDecimal("25.45"),((ProductResponse) response.getBody()).getPrice());
		verify(productService, times(1)).findProductToApply(brandId, LocalDateTime.parse(date, formatter), productId);
		verify(productRequestValidator, times(1)).IsValidIdRequest(brandId, date, productId);
	}
	
	@Test
	public void getProductToApplyTest4() throws ProductServiceException {
		//Given
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		Integer brandId = 1;
		String date = "2020-06-15T10:00:00";
		Long productId = 35455L;
		
		Optional<Product> product = productRepository.findById(3L);
		when(productRequestValidator.IsValidIdRequest(brandId, date, productId)).thenReturn(new ArrayList<ProductValidationError>());
		when(productService.findProductToApply(brandId, LocalDateTime.parse(date, formatter), productId)).thenReturn(product);
		
		//When
		ResponseEntity<?> response = productController.getProductToApply(brandId, date, productId);
		
		//Then
		assertNotNull(response);
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(new BigDecimal("30.50"),((ProductResponse) response.getBody()).getPrice());
		verify(productService, times(1)).findProductToApply(brandId, LocalDateTime.parse(date, formatter), productId);
		verify(productRequestValidator, times(1)).IsValidIdRequest(brandId, date, productId);
	}
	
	@Test
	public void getProductToApplyTest5() throws ProductServiceException {
		//Given
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		Integer brandId = 1;
		String date = "2020-06-16T21:00:00";
		Long productId = 35455L;
		
		Optional<Product> product = productRepository.findById(4L);
		when(productRequestValidator.IsValidIdRequest(brandId, date, productId)).thenReturn(new ArrayList<ProductValidationError>());
		when(productService.findProductToApply(brandId, LocalDateTime.parse(date, formatter), productId)).thenReturn(product);
		
		//When
		ResponseEntity<?> response = productController.getProductToApply(brandId, date, productId);
		
		//Then
		assertNotNull(response);
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(new BigDecimal("38.95"),((ProductResponse) response.getBody()).getPrice());
		verify(productService, times(1)).findProductToApply(brandId, LocalDateTime.parse(date, formatter), productId);
		verify(productRequestValidator, times(1)).IsValidIdRequest(brandId, date, productId);
	}
	
}
