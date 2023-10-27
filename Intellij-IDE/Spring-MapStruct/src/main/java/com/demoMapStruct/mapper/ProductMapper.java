package com.demoMapStruct.mapper;

import com.demoMapStruct.dto.ProductDto;
import com.demoMapStruct.model.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",imports = UUID.class)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

//    @Mapping(target = "itemID", expression = "java(UUID.randomUUID().toString())")
//    @Mapping(source = "product.items", target = "itemsList")
    @Mapping(source = "product.descr", target = "description", defaultValue = "description")

    ProductDto modelToDto(Product product);

    List<ProductDto> modelsToDtos(List<Product> productList);

//    @Mapping(source = "productDto.description", target = "desc", defaultValue = "description")
    @InheritInverseConfiguration
    Product dtoToModel(ProductDto productDto);

}
