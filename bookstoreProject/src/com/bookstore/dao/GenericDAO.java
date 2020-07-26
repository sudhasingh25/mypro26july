package com.bookstore.dao;

import java.util.List;

public interface GenericDAO<E> {
	
	public E create(E entity);
	
	public void delete(Object id);
	
	public long count();
	
	public E get(Object id);
	
	public E update(E entity);
	
	public List<E> listAll();
}
