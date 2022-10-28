package com.inditex.product.utils;

import com.inditex.product.model.Product;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

public class DbUtils {

  public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
  public static final String CURRENCY = "EUR";

  public static List<Product> getProducts() {
    Product p1 = Product.builder()
        .id(1L)
        .brandId(1)
        .startDate(LocalDateTime.parse("2020-06-14T00:00:00", FORMATTER))
        .endDate(LocalDateTime.parse("2020-12-31T23:59:59", FORMATTER))
        .priceList(1)
        .productId(35455L)
        .priority(0)
        .price(new BigDecimal("35.50"))
        .currency(Currency.getInstance(CURRENCY))
        .build();

    Product p2 = Product.builder()
        .id(2L)
        .brandId(1)
        .startDate(LocalDateTime.parse("2020-06-14T15:00:00", FORMATTER))
        .endDate(LocalDateTime.parse("2020-06-14T18:30:00", FORMATTER))
        .priceList(2)
        .productId(35455L)
        .priority(1)
        .price(new BigDecimal("25.45"))
        .currency(Currency.getInstance(CURRENCY))
        .build();

    Product p3 = Product.builder()
        .id(3L)
        .brandId(1)
        .startDate(LocalDateTime.parse("2020-06-15T00:00:00", FORMATTER))
        .endDate(LocalDateTime.parse("2020-06-15T11:00:00", FORMATTER))
        .priceList(3)
        .productId(35455L)
        .priority(1)
        .price(new BigDecimal("30.50"))
        .currency(Currency.getInstance(CURRENCY))
        .build();

    Product p4 = Product.builder()
        .id(4L)
        .brandId(1)
        .startDate(LocalDateTime.parse("2020-06-15T16:00:00", FORMATTER))
        .endDate(LocalDateTime.parse("2020-12-31T23:59:59", FORMATTER))
        .priceList(4)
        .productId(35455L)
        .priority(1)
        .price(new BigDecimal("38.95"))
        .currency(Currency.getInstance(CURRENCY))
        .build();

    return new ArrayList<>(Arrays.asList(p1, p2, p3, p4));
  }

}
