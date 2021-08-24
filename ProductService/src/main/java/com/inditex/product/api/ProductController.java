package com.inditex.productService.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.productService.exception.ProductServiceException;
import com.inditex.productService.mapper.IProductMapper;
import com.inditex.productService.model.Product;
import com.inditex.productService.model.dto.ProductResponse;
import com.inditex.productService.model.dto.ProductValidationError;
import com.inditex.productService.service.IProductService;
import com.inditex.productService.validator.IProductRequestValidator;


@RestController
@RequestMapping(path = "/product")
public class ProductController {
    private Logger log = LoggerFactory.getLogger(ProductController.class);
    
    private IProductService productService;   
    private IProductRequestValidator productRequestValidator;
    
    
    @Autowired
    public ProductController(IProductService productService, IProductRequestValidator productRequestValidator) {
		this.productService = productService;
		this.productRequestValidator = productRequestValidator;
	}

	@GetMapping("/apply")
    public ResponseEntity<?> getProductToApply(@RequestParam Integer brandId, @RequestParam String date, @RequestParam Long productId) throws ProductServiceException  {
		
    	try {
    		
    		List<ProductValidationError> validationErrors = productRequestValidator.IsValidIdRequest(brandId, date, productId);
    		if (!validationErrors.isEmpty())	
    			return new ResponseEntity<List<ProductValidationError>>(validationErrors, HttpStatus.BAD_REQUEST);
    		
    		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    		LocalDateTime parseDate = LocalDateTime.parse(date, dateTimeFormatter);
    		
    		Optional<Product> product = productService.findProductToApply(brandId, parseDate, productId);
    		if (!product.isPresent()) {
    			log.debug("Not found the product to apply with id: " + productId);
    			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    		}
    		else {
    			ProductResponse response = IProductMapper.INSTANCE.toProductResponseDto(product.get());
    			
    			log.info("The product to apply with id " + productId + " has been found.");
    	    	return new ResponseEntity<>(response, HttpStatus.OK);
			}
    		
    	} catch (Exception e) {
    		log.error("An error occurred while searching the product to apply with id: " + productId, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
}
