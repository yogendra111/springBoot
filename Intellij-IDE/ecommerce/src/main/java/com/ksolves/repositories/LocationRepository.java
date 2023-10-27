package com.ksolves.repositories;

import com.ksolves.entities.Location;
import com.ksolves.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
