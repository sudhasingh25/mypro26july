package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Book;

public class BookDAO extends JpaDAO<Book> implements GenericDAO<Book> {

	public BookDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Book create(Book entity) {
		entity.setLastUpdateTime(new Date());
		return super.create(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(Book.class, id);
	}

	@Override
	public long count() {
		return (long)super.countWithNamedQuery("Book.countAll");
	}

	@Override
	public Book get(Object id) {
		return super.find(Book.class, id);
	}

	@Override
	public Book update(Book entity) {
		entity.setLastUpdateTime(new Date());
		return super.update(entity);
	}

	@Override
	public List<Book> listAll() {
		return super.findWithNamedQuery("Book.findAll");
	}

	public Book findByTitle(String title) {
		List<Book> listbook=super.findWithNamedQuery("Book.findByTitle","title",title);
		if(listbook.size()>0){
			return listbook.get(0);
		}
		return null;
	}

	
}
