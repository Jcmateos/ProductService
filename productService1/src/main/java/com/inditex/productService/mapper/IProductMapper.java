package com.inditex.productService.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.inditex.productService.dto.ProductResponse;
import com.inditex.productService.model.Product;

@Mapper(componentModel = "spring")
public interface IProductMapper {
	
	IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);
	
	ProductResponse toProductResponseDto(Product target);
}
