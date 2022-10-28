package com.inditex.product.service.impl;

import static com.inditex.product.utils.DbUtils.FORMATTER;
import static com.inditex.product.utils.DbUtils.getProducts;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.inditex.product.exception.ProductServiceException;
import com.inditex.product.model.Product;
import com.inditex.product.repository.IProductRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @InjectMocks
  private ProductServiceImpl productService;

  @Mock
  private IProductRepository productRepositoryMock;

  @Autowired
  private IProductRepository productRepository;

  @BeforeEach
  public void saveProducts() {
    productRepository.saveAll(getProducts());
  }

  @ParameterizedTest
  @CsvSource({
      "2020-06-14T10:00:00,35.50", // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
      "2020-06-14T16:00:00,25.45", // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
      "2020-06-14T21:00:00,35.50", // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
      "2020-06-15T10:00:00,30.50", // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
      "2020-06-16T21:00:00,38.95", // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
  })
  void given_RequestParams_when_FindProductToApply_then_ObtainProductResponse(final String date, final String prize) throws ProductServiceException {
    // Given:
    Integer brandId = 1;
    Long productId = 35455L;
    LocalDateTime localDateTime = LocalDateTime.parse(date, FORMATTER);

    List<Product> listProducts = productRepository.findAll();
    when(productRepositoryMock.findAll()).thenReturn(listProducts);

    // When:
    Optional<Product> response = productService.findProductToApply(brandId, localDateTime, productId);

    // Then:
    assertNotNull(response);
    assertEquals(new BigDecimal(prize), response.get().getPrice());
    verify(productRepositoryMock, times(1)).findAll();
  }

}
