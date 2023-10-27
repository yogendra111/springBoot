package com.demoMapStruct.mapper;

import com.demoMapStruct.dto.ProductDto;
import com.demoMapStruct.model.Product;

import java.util.List;

public class ProductMapperOldImpl implements ProductMapper{

    @Override
    public ProductDto modelToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        return productDto;
    }

    @Override
    public List<ProductDto> modelsToDtos(List<Product> productList) {
        return null;
    }

    @Override
    public Product dtoToModel(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(product.getQuantity());
        return product;
    }

}
