package com.ksolves.controllers;

import com.ksolves.exceptionhandler.ProductNotFoundException;
import com.ksolves.models.ProductModel;
import com.ksolves.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductModel productModel){
        productService.saveProduct(productModel);
        return new ResponseEntity<>("Product added Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/product/{pid}")
    public ResponseEntity<?> getProduct(@PathVariable("pid") Long productId) throws ProductNotFoundException {
        return ResponseEntity.ok().body(productService.getProductById(productId));
    }

    @GetMapping("/product")
    public ResponseEntity<?> getAllProduct(){
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

    @DeleteMapping("/product/{pid}")
    public ResponseEntity<?> deleteProductById(@PathVariable("pid") Long productId) throws ProductNotFoundException {
        productService.deleteProductById(productId);
        return ResponseEntity.ok().body("Product deleted Successfully");
    }

    @PatchMapping("/product/{pid}")
    public ResponseEntity<?> updateProductById(@PathVariable("pid") Long productId, @RequestBody ProductModel productModel) throws ProductNotFoundException {
        return ResponseEntity.accepted().body(productService.updateProduct(productId, productModel));
    }

}
