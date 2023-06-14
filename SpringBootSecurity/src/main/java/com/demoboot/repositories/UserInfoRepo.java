package com.demoboot.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demoboot.entities.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {
	Optional<UserInfo> findByName(String name);
}
