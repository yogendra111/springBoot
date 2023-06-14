package com.demoboot.services;

import java.util.List;

import com.demoboot.entities.Book;
import com.demoboot.models.BookModel;

public interface BookService {
	public Book createBook(BookModel bookModel);
	public void deleteBook(Integer id);
	public BookModel getBook(Integer id);
	public Book updateBook(BookModel bookModel);
	public List<BookModel> getAllBook();
}
