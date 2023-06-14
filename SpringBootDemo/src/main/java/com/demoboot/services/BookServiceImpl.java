package com.demoboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.demoboot.daos.BookDao;
import com.demoboot.entities.Book;
import com.demoboot.exceptionhandler.BookNotFoundException;
import com.demoboot.models.BookModel;

@Service
@Profile("dev")
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookDao bookDao;

	@Override
	public Book createBook(BookModel bookModel) {
		return bookDao.save(convertBookModeltoBook(bookModel));
	}

	@Override
	public void deleteBook(Integer id) {
		bookDao.deleteById(id);
	}

	@Override
	public BookModel getBook(Integer id) {
		 Optional<Book> book = bookDao.findById(id);
		 if(book.isPresent()) {
			 return convertBooktoBookModel(book.get());
		 }else {
			 throw new BookNotFoundException("Book not Found");
		 }
	}

	@Override
	public Book updateBook(BookModel bookModel) {
		return bookDao.save(convertBookModeltoBook(bookModel));		
	}

	@Override
	public List<BookModel> getAllBook() {
		List<BookModel> books = new ArrayList<>();
		for(Book book : bookDao.findAll())
		{
			books.add(convertBooktoBookModel(book));
		}
		return books;
	}
	
	public Book convertBookModeltoBook(BookModel bookModel) {
		Book book = new Book();
		book.setId(bookModel.getId());
		book.setName(bookModel.getName());
		book.setAuthor(bookModel.getAuthor());
		book.setPrice(bookModel.getPrice());
		return book;
	}
	
	public BookModel convertBooktoBookModel(Book book) {
		BookModel bookModel = new BookModel();
		bookModel.setId(book.getId());
		bookModel.setName(book.getName());
		bookModel.setAuthor(book.getAuthor());
		bookModel.setPrice(book.getPrice());
		return bookModel;
	}

}
