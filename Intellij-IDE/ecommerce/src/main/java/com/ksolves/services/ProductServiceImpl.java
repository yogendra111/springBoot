package com.ksolves.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksolves.entities.Product;
import com.ksolves.exceptionhandler.ProductNotFoundException;
import com.ksolves.mapper.ProductMapper;
import com.ksolves.models.ProductModel;
import com.ksolves.redis.CustomRedisSerializer;
import com.ksolves.repositories.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    static final String KEY = "Products";

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    ObjectMapper mapper;

    private HashOperations<String, Long, Product> ops;

    @PostConstruct
    public void initialization() {
        CustomRedisSerializer<Product> serializer = new CustomRedisSerializer<Product>(mapper,Product.class);
        redisTemplate.setHashValueSerializer(serializer);
        ops = redisTemplate.opsForHash();
    }

    @Override
    public void saveProduct(ProductModel productModel) {
        Product product = productRepository.save(ProductMapper.convertProductModelToProduct(productModel));
        ops.put(KEY, product.getId(), product);
    }

    @Override
    public ProductModel getProductById(Long productId) {
        ProductModel productModel = null;
//        if(ops.hasKey(KEY, productId)) {
            productModel = ProductMapper.convertProductToProductModel((Product) ops.get(KEY, productId));
//        }else{
//            if (!productRepository.existsById(productId)) {
//                throw new ProductNotFoundException();
//            }
//            Product product = productRepository.findById(productId).get();
//            ops.put(KEY, product.getId(), product);
//            productModel = ProductMapper.convertProductToProductModel(product);
//        }
        return productModel;
    }

    @Override
    public void deleteProductById(Long productId) {
        if(productRepository.existsById(productId)){
            productRepository.deleteById(productId);
            ops.delete(KEY, productId);
        }else{
            throw new ProductNotFoundException("Product Not found to delete");
        }
    }

    @Override
    public List<ProductModel> getAllProduct() {
        List<ProductModel> products = null;
//        if(productRepository.count()>ops.size(KEY)){
//            products = productRepository.findAll().stream()
//                    .peek(product -> ops.putIfAbsent(KEY, product.getId(), product))
//                    .map(ProductMapper::convertProductToProductModel)
//                    .collect(Collectors.toList());
//        }else{
            products = ops.entries(KEY).values()
                    .stream().map(product -> ProductMapper.convertProductToProductModel(product)).toList();
//        }
        return products;
    }

    @Override
    public ProductModel updateProduct(Long productId, ProductModel productModel) {
        if(!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not Found to update.");
        }
        Product product = productRepository.findById(productId).get();
//        Product product = ops.get(KEY, productId);
        product.setName(productModel.getName());
        product.setPrice(productModel.getPrice());
        product.setBrand(productModel.getBrand());
        Product updatedProduct = productRepository.save(product);
        ops.put(KEY, productId, updatedProduct);
        return ProductMapper.convertProductToProductModel(updatedProduct);
    }

}
