package com.java.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.model.Book;

@Repository
public interface BookRepository<B> extends JpaRepository<Book,Long> {

//	public void persist(T entity);
//	
//	public void update(T entity);
//	
//	public T findById(Id id);
//	
//	public void delete(T entity);
//	
//	public List<T> findAll();
//	
//	public void deleteAll();
	
	public List<Book> findByTitle(String title);
	
	public Book findByBookId(long bookId);
	
	@Query("SELECT b FROM Book b WHERE b.price >= :price")
	public List<Book> findByPrice(@Param("price") double price);
	
}
