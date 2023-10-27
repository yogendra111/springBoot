package com.ksolves.services;

import com.ksolves.exceptionhandler.ProductAlreadyExistsException;
import com.ksolves.exceptionhandler.ProductNotFoundException;
import com.ksolves.models.ProductModel;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductModel product);
    ProductModel getProductById(Long productId) throws ProductNotFoundException;
    void deleteProductById(Long productId) throws ProductNotFoundException;
    List<ProductModel> getAllProduct();
    ProductModel updateProduct(Long productId, ProductModel productModel) throws ProductNotFoundException;

}
