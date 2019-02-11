package com.java.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.dao.BookRepository;
import com.java.model.Book;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase
//@ContextConfiguration(classes = RestfulApplication.class)
@TestPropertySource(locations = {"classpath:applicationtest.properties"})
//@SpringBootTest(classes = RestfulApplication.class)
//@SpringBootConfiguration
public class BookRepositoryTest {

	@Autowired 
	private TestEntityManager entityManager;
	
	@Autowired
	private BookRepository<Book> bookRepository;
	
	@Test
	public void testBookId() throws ParseException {
		Book book = getBook();
		Book saveBook = entityManager.persist(book);
		Book getBook = bookRepository.findByBookId(saveBook.getBookId());
		Assert.assertEquals(saveBook, getBook);
	}
	
	@Test
	public void testBookUpdate() throws ParseException {
		Book book = getBook();
		Book saveBook = entityManager.persist(book);
		long bookVolume = saveBook.getVolume();
		book.setVolume(11);
		Book updateBook = entityManager.merge(book);
		Assert.assertNotEquals(bookVolume, updateBook.getVolume());
	}
	
	@Test
	public void testBookDelete() throws ParseException {
		Book book = getBook();
		entityManager.persist(book);
		entityManager.remove(entityManager.find(Book.class, 1L));
		Assert.assertNull(entityManager.find(Book.class, 1L));
	}
	
	private Book getBook() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date bookDate = sdf.parse("2010-04-04");
		Book book = new Book("Wings Of Fire", 400.0, 2, bookDate);
		book.setBookId(1);
		return book;
	}
}
