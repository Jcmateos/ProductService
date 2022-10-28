package com.inditex.product.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Version;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product implements Serializable {

  private static final long serialVersionUID = 3924422419041778871L;

  @Id
  @Setter(AccessLevel.NONE)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Version
  private Long version;

  @Column(name = "brand_id")
  private Integer brandId;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "end_date")
  private LocalDateTime endDate;

  @Column(name = "price_list")
  private Integer priceList;

  @Column(name = "product_id")
  private Long productId;

  private Integer priority;

  private BigDecimal price;

  @Column(name = "curr")
  private Currency currency;

}
