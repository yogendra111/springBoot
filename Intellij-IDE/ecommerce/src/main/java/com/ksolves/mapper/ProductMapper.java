package com.ksolves.mapper;

import com.ksolves.entities.Product;
import com.ksolves.models.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static Product convertProductModelToProduct(ProductModel productModel){
        Product product = new Product();
        product.setId(productModel.getId());
        product.setName(productModel.getName());
        product.setPrice(productModel.getPrice());
        product.setBrand(productModel.getBrand());
        return product;
    }

    public static ProductModel convertProductToProductModel(Product product){
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setName(product.getName());
        productModel.setPrice(product.getPrice());
        productModel.setBrand(product.getBrand());
        return productModel;
    }

}
