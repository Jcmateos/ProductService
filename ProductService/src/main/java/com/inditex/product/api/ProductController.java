package com.inditex.product.api;

import com.inditex.product.mapper.IProductMapper;
import com.inditex.product.model.Product;
import com.inditex.product.model.dto.ProductResponse;
import com.inditex.product.service.IProductService;
import com.inditex.product.validator.IProductRequestValidator;
import com.inditex.product.validator.ValidationError;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/product")
public class ProductController {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  private IProductService productService;
  private IProductRequestValidator productRequestValidator;

  @GetMapping("/apply")
  public ResponseEntity<?> getProductToApply(@RequestParam final Integer brandId, @RequestParam final String date,
      @RequestParam final Long productId) {
    try {
      List<ValidationError> validationErrors = productRequestValidator.IsValidIdRequest(brandId, date, productId);
      if (!validationErrors.isEmpty()) {
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
      }

      LocalDateTime parseDate = LocalDateTime.parse(date, FORMATTER);

      Optional<Product> product = productService.findProductToApply(brandId, parseDate, productId);
      if (product.isEmpty()) {
        log.debug("Not found the product to apply with id: {}", productId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
        ProductResponse response = IProductMapper.INSTANCE.toProductResponseDto(product.get());

        log.info("The product to apply with id {} has been found.", productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
    } catch (Exception e) {
      log.error("An error occurred while searching the product to apply with id: {} ", productId, e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
