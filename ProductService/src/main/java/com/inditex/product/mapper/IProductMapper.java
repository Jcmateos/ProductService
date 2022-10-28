package com.inditex.product.mapper;

import com.inditex.product.model.Product;
import com.inditex.product.model.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IProductMapper {

  IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);

  ProductResponse toProductResponseDto(final Product target);

}
