package com.ksolves.repositories;

import com.ksolves.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByName(String name);
    boolean existsByName(String name);
}
