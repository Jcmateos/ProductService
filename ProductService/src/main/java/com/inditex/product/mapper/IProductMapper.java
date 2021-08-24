package com.inditex.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.inditex.product.model.Product;
import com.inditex.product.model.dto.ProductResponse;

@Mapper(componentModel = "spring")
public interface IProductMapper {
	
	IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);
	
	ProductResponse toProductResponseDto(Product target);
}
