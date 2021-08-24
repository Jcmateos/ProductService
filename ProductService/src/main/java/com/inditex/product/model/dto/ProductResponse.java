package com.inditex.productService.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

	private Integer brandId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Integer priceList;
	private Long productId;
	private BigDecimal price;
	
}
