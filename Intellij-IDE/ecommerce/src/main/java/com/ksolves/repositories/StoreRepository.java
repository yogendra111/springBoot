package com.ksolves.repositories;

import com.ksolves.entities.Product;
import com.ksolves.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
