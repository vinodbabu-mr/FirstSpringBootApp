//package com.java.dao;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.java.model.Book;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase
////@ContextConfiguration(classes = RestfulApplication.class)
////@TestPropertySource(locations = {"classpath:applicationtest.properties"})
////@SpringBootTest(classes = RestfulApplication.class)
////@SpringBootConfiguration
//public class BookRepositoryTest {
//
//	@Autowired 
//	private TestEntityManager entityManager;
//	
//	@Autowired
//	private BookRepository<Book> bookRepository;
//	
//	@Test
//	public void testBookId() throws ParseException {
//		Book book = getBook();
//		Book saveBook = entityManager.persist(book);
//		Book getBook = bookRepository.findByBookId(saveBook.getBookId());
//		Assert.assertEquals(saveBook, getBook);
//	}
//	
//	private Book getBook() throws ParseException {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//		Date bookDate = sdf.parse("2010-04-04");
//		Book book = new Book("Wings Of Fire", 400.0, 2, bookDate);
//		book.setBookId(1);
//		return book;
//	}
//}
