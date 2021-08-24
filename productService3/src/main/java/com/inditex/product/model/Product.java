package com.inditex.product.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.Version;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "PRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 3924422419041778871L;
	
	@Id
	@Setter(AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version;
	
	@Column(name = "BRAND_ID")
	private Integer brandId;
	
 	@Column(name = "START_DATE")
 	private OffsetDateTime startDate;
 	
 	@Column(name = "END_DATE")
 	private OffsetDateTime endDate;
 	
 	@Column(name = "PRICE_LIST")
 	private Integer priceList;
 	
 	@Column(name = "PRODUCT_ID")
 	private Long productId;
 	
 	@Column(name = "PRIORITY")
 	private Integer priority;
 	
 	@Column(name = "PRICE")
 	private BigDecimal price;
 	
 	@Column(name = "CURR")
 	private Currency currency;
	
}
