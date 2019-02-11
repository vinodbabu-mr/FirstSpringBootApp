package com.java.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.dao.BookRepository;
import com.java.model.Book;
import com.java.model.Books;




@Service
@RestController
@Profile("CloudX")
public class BookService {
	
	@Autowired
	private BookRepository<Book> bookRepo;
	private static final String SUCCESS_STATUS = "success";
	 private static final String ERROR_STATUS = "error";
	 private static final int CODE_SUCCESS = 100;
	 private static final int AUTH_FAILURE = 102;
	 @RequestMapping(value = "/bookss", method = RequestMethod.GET)
	 public Books getBooks() {
		 Books response = new Books();
	  List<Book> bookList = new ArrayList<Book>();
		bookList = bookRepo.findAll();
		response.setBook(bookList);
	   response.setStatus(SUCCESS_STATUS);
	   response.setCode(CODE_SUCCESS);
	  return response;
	 }
	 
	 @RequestMapping(value = "/bookss/{id}", method = RequestMethod.GET)
	 public Books getBookById(@PathVariable("id") Long bookId) {
		 Books response = new Books();
	  List<Book> bookList = new ArrayList<Book>();
		Book book = bookRepo.findByBookId(bookId);
		bookList.add(book);
		response.setBook(bookList);
	   response.setStatus(SUCCESS_STATUS);
	   response.setCode(CODE_SUCCESS);
	  return response;
	 }
	 @RequestMapping(value="/addBook", method = RequestMethod.POST)
	 @Consumes(MediaType.APPLICATION_JSON_VALUE)
	 @Produces(MediaType.APPLICATION_JSON_VALUE)
	 public Books addBook(@RequestBody Book book) {
		 Books response = new Books();
		 book = bookRepo.save(book);
		 List<Book> bookLst = new ArrayList<>();
		 bookLst.add(book);
		 response.setBook(bookLst);
   	     response.setStatus(SUCCESS_STATUS);
		 response.setCode(CODE_SUCCESS);
		 return response;
	 }
	 @RequestMapping(value="/modifyBook", method = RequestMethod.PUT)
	 @Consumes(MediaType.APPLICATION_JSON_VALUE)
	 @Produces(MediaType.APPLICATION_JSON_VALUE)
	 public Books updateBook(@RequestBody Book book) {
		 Books response = new Books();
		 book = bookRepo.save(book);
		 List<Book> bookLst = new ArrayList<>();
		 bookLst.add(book);
		 response.setBook(bookLst);
   	     response.setStatus(SUCCESS_STATUS);
		 response.setCode(CODE_SUCCESS);
		 return response;
	 }
	 
	 @RequestMapping(value="/deleteBook/{id}", method = RequestMethod.DELETE)
	 @Consumes(MediaType.APPLICATION_JSON_VALUE)
	 @Produces(MediaType.APPLICATION_JSON_VALUE)
	 public Books deleteBook(@PathVariable("id") Long bookId) {
		 Books response = new Books();
		 bookRepo.deleteById(bookId);
		 response.setStatus("Book Deleted Successfully");
		 response.setCode(CODE_SUCCESS);
		 return response;
	 }
}


