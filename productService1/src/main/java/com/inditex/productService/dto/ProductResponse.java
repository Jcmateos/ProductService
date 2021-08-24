package com.inditex.productService.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

	private Long productId;
	private Integer brandId;
	private Integer priceList;
	private OffsetDateTime startDate;
	private OffsetDateTime endDate;
	private BigDecimal price;
	
}
