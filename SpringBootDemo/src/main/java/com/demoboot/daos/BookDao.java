package com.demoboot.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demoboot.entities.Book;

public interface BookDao extends JpaRepository<Book, Integer>{
	Optional<Book> findByName(String name);
}
