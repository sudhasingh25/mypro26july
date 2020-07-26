package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Category;

public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void delete(Object categoryId) {
		super.delete(Category.class, categoryId);
	}

	@Override
	public long count() {
		return (long)super.countWithNamedQuery("Category.countAll");
	}

	@Override
	public Category get(Object categoryId) {
		return super.find(Category.class, categoryId);
	}

	@Override
	public List<Category> listAll() {
		return super.findWithNamedQuery("Category.findAll");
	}
	
	public Category create(Category category){
		return super.create(category);
	}
	
	public Category update(Category category){
		return super.update(category);
	}
	
	public Category findByName(String catname){
		//System.out.println("find by name called1");
		List<Category> listCategory=super.findWithNamedQuery("Category.findByName","name",catname);
		//System.out.println("find by name called2");

		if(listCategory !=null && listCategory.size()>0){
			//System.out.println("find by name called3");

			return listCategory.get(0);
			
		}
		//System.out.println("find by name called4");
		return null;
		
	}

}
