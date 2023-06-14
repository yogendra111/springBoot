package com.demoboot.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demoboot.entities.Book;
import com.demoboot.models.BookModel;
import com.demoboot.services.BookService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@RestController
public class MainController {
	
	@Autowired
	private BookService bookService;
	@Value("${developer.name}")
	private String devName;
	
	@PostConstruct
	public void init() {
		System.out.println("Bean Initialized, Welcome " + devName);
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Bean will destroy, Thankyou " + devName);
	}
	
	@PostMapping("/addbook")
	public ResponseEntity<Book> addBook(@RequestBody BookModel bookModel) {
		return new ResponseEntity<Book>(bookService.createBook(bookModel), HttpStatus.OK);
	}

//	@PostMapping("/deletebook")
//	public void deleteBook(@RequestBody Integer bID) {
//		 new ResponseEntity(bookService.deleteBook(bID), HttpStatus.OK);
//	}

	@GetMapping("/getbook/{id}")
	public ResponseEntity<BookModel> getBook(@PathVariable int id) {
		 return new ResponseEntity<BookModel>(bookService.getBook(id), HttpStatus.OK);
	}

	@GetMapping("/getallbooks")
	public ResponseEntity<List<BookModel>> getAllBook() {
		 return new ResponseEntity<List<BookModel>>(bookService.getAllBook(), HttpStatus.OK);
	}
	
//	@GetMapping("/getbookandprice")
//	public ResponseEntity<List<BookModel>> getBookAndPrice() {
//		 Map<String, V>
//		  bookService.getAllBook();
//		 
//		 return new ResponseEntity;
//	}

}
