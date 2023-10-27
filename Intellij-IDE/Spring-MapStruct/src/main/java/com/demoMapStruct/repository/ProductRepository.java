package com.demoMapStruct.repository;

import com.demoMapStruct.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
