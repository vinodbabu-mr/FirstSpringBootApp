package com.java.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.dao.BookRepository;
import com.java.model.Book;
import com.java.model.Books;
import com.java.rest.RestfulApplication;

import org.junit.Assert;
import org.junit.Before;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RestfulApplication.class)
@SpringBootTest
@ActiveProfiles("CloudX")
public class BookServiceTest {

	@Autowired
	private BookService bookService;

	@MockBean
	private BookRepository<Book> bookRepo;
	
	Book book;
	List<Book> bookList  = new ArrayList<Book>();
	Books response;
	
	@Before
	public void setUp() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date bookDate = sdf.parse("2010-04-04");
		book = new Book("Wings Of Fire", 400.0, 2, bookDate);
		book.setBookId(1);
		bookList.add(book);
		response = new Books();
		response.setBook(bookList);
		response.setStatus("SUCCESS");
		response.setCode(101);
	}

	@Test
	public void testGetBooks() {
		Mockito.when(bookRepo.findAll()).thenReturn(bookList);
		Assert.assertEquals(bookService.getBooks().getBook(),bookList);
	}
	
	@Test
	public void testGetBookById() throws ParseException {
		Mockito.when(bookRepo.findByBookId(1)).thenReturn(book);
		Assert.assertEquals(bookService.getBookById(1L).getBook().get(0).getBookId(), book.getBookId());
	}
	
	@Test
	public void testAddBook() {
		Mockito.when(bookRepo.save(book)).thenReturn(book);
		Assert.assertEquals(bookService.addBook(book).getBook().get(0).getBookId(), book.getBookId());
	}
	
	@Test
	public void testUpdateBook() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date bookDate = sdf.parse("2010-04-04");
		Book updatedBook = new Book("Wings Of Fire", 400.0, 10, bookDate);
		Mockito.when(bookRepo.save(book)).thenReturn(updatedBook);
		Assert.assertNotEquals(bookService.updateBook(book).getBook().get(0).getVolume(), book.getVolume());
	}
	
	@Test
	public void testDeleteBook() throws Exception {
		Mockito.doNothing().when(bookRepo).deleteById(1L);
		Assert.assertNotEquals(bookService.deleteBook(1L).getBook(), book);
	}
	

}
