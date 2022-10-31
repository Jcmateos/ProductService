package com.inditex.product.service.impl;

import static com.inditex.product.exception.ProductExceptionType.UNKNOWN_DATABASE_ERROR;

import com.inditex.product.exception.ProductServiceException;
import com.inditex.product.exception.UnknownDatabaseError;
import com.inditex.product.model.Product;
import com.inditex.product.repository.IProductRepository;
import com.inditex.product.service.IProductService;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

  private IProductRepository productRepository;

  @Transactional(readOnly = true)
  public Optional<Product> findProductToApply(final Integer brandId, final LocalDateTime date, final Long productId) throws ProductServiceException {
    try {
      List<Product> listProducts = productRepository.findAll();

      return listProducts.stream()
          .filter(p -> p.getProductId().equals(productId)
              && p.getBrandId().equals(brandId)
              && p.getStartDate().compareTo(date) <= 0
              && p.getEndDate().compareTo(date) >= 0)
          .max(Comparator.comparing(Product::getPriority));
    } catch (Exception e) {
      String error = MessageFormat.format(
          "An error occurred when find product to apply for brandId {0}, productId {1} and date {2}.", brandId, productId, date);
      log.error(error);
      throw new UnknownDatabaseError(UNKNOWN_DATABASE_ERROR, error);
    }
  }

}
