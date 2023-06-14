package com.demoboot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookId")
	private Integer id;
	
	@Column(name = "bookName")
	private String name;
	
	@Column(name = "bookAuthor")
	private String author;
	
	@Column(name = "bookPrice")
	private Double price;

}
