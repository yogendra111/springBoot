package com.demoMapStruct.controller;

import com.demoMapStruct.dto.ProductDto;
import com.demoMapStruct.mapper.ProductMapper;
import com.demoMapStruct.model.Product;
import com.demoMapStruct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<Product> save(@RequestBody ProductDto productDto){
        return new ResponseEntity<>(productRepository.save(
                productMapper.dtoToModel(productDto)
        ), HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> findAll(){
        return new ResponseEntity<>(productMapper.modelsToDtos(productRepository.findAll()),HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> findById(@PathVariable("productId") Integer id){
        return new ResponseEntity<>(productMapper.modelToDto(productRepository.findById(id).get()),HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteById(@PathVariable("productId") Integer id){
//        ProductDto productDto = productMapper.modelToDto(productRepository.findById(id).get());
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
