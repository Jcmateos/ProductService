package com.inditex.productService.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.inditex.productService.model.Product;
import com.inditex.productService.model.dto.ProductResponse;

@Mapper(componentModel = "spring")
public interface IProductMapper {
	
	IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);
	
	@Mapping(source = "brandId", target = "brandId")
	@Mapping(source = "startDate", target = "startDate")
	@Mapping(source = "endDate", target = "endDate")
	@Mapping(source = "priceList", target = "priceList")
	@Mapping(source = "productId", target = "productId")
	@Mapping(source = "price", target = "price")
	ProductResponse toProductResponseDto(Product target);
}
