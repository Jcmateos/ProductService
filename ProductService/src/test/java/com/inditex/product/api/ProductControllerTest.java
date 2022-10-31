package com.inditex.product.api;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.inditex.product.exception.ProductServiceException;
import com.inditex.product.model.Product;
import com.inditex.product.model.dto.ProductResponse;
import com.inditex.product.repository.IProductRepository;
import com.inditex.product.service.IProductService;
import com.inditex.product.validator.IProductRequestValidator;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

@Sql({"/data.sql"})
@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

  public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  @InjectMocks
  private ProductController productController;

  @Mock
  private IProductService productService;

  @Mock
  private IProductRequestValidator productRequestValidator;

  @Autowired
  private IProductRepository productRepository;

  @ParameterizedTest
  @CsvSource({
      "1,2020-06-14T10:00:00,35.50", // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
      "2,2020-06-14T16:00:00,25.45", // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
      "1,2020-06-14T21:00:00,35.50", // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
      "3,2020-06-15T10:00:00,30.50", // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
      "4,2020-06-16T21:00:00,38.95", // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
  })
  void given_RequestParams_when_FindProductToApply_then_ObtainOkResponse(final String id, final String date, final String prize)
      throws ProductServiceException {
    // Given:
    Integer brandId = 1;
    Long productId = 35455L;

    Optional<Product> product = productRepository.findById(Long.valueOf(id));
    when(productRequestValidator.IsValidIdRequest(brandId, date, productId)).thenReturn(new ArrayList<>());
    when(productService.findProductToApply(brandId, LocalDateTime.parse(date, FORMATTER), productId)).thenReturn(product);

    // When:
    ResponseEntity<?> response = productController.getProductToApply(brandId, date, productId);

    // Then:
    assertAll("response",
        () -> assertNotNull(response),
        () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
        () -> assertEquals(new BigDecimal(prize), ((ProductResponse) response.getBody()).getPrice()),
        () -> assertEquals(brandId, ((ProductResponse) response.getBody()).getBrandId()),
        () -> assertEquals(productId, ((ProductResponse) response.getBody()).getProductId())
    );
    verify(productService, times(1)).findProductToApply(brandId, LocalDateTime.parse(date, FORMATTER), productId);
    verify(productRequestValidator, times(1)).IsValidIdRequest(brandId, date, productId);
  }

}
