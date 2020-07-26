package com.bookstore.controller.admin.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryService {

	private  EntityManager entityManager;
	private  EntityManagerFactory entityManagerFactory;
	private  CategoryDAO categoryDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	
	public CategoryService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		entityManagerFactory=Persistence.createEntityManagerFactory("BooksStoreWebsite");
		entityManager=entityManagerFactory.createEntityManager();
		categoryDao=new CategoryDAO(entityManager);
	}

	public void createCategory() throws ServletException, IOException {
		String name=request.getParameter("name");
		Category existCat=categoryDao.findByName(name);
		//if(existCat!=null){
		//System.out.println("*******************"+existCat.getName());}
		if(existCat!=null){
			String message="Category name "+name+" is Already exist";
			request.setAttribute("message", message);

			RequestDispatcher dis=request.getRequestDispatcher("message.jsp");
			dis.forward(request, response);
		}else{
			Category newcategory=new Category(name);
			categoryDao.create(newcategory);
			listCategory("New Category has been created successfully.");
		}
	}
	
	public void updateCategory() throws ServletException, IOException{
		Integer categoryId=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		
		Category categoryByName=categoryDao.findByName(name);
		Category categoryById=categoryDao.get(categoryId);
		
		if(categoryByName!=null && categoryById.getCategoryId()!=categoryByName.getCategoryId()){
			String message="Category with name "+name+" already exist.";
			request.setAttribute("message", message);
			RequestDispatcher dis=request.getRequestDispatcher("message.jsp");
			dis.forward(request, response);
		}else{
			Category newCategory=new Category(categoryId,name);
			categoryDao.update(newCategory);
			listCategory("Category has been updated successfully.");

		}
	}
	
	public void listCategory() throws ServletException, IOException{
		listCategory(null);
	}

	public void listCategory(String message) throws ServletException, IOException{
		List<Category> listcategory=categoryDao.listAll();
		request.setAttribute("listcategory", listcategory);
		request.setAttribute("message", message);

		RequestDispatcher dis=request.getRequestDispatcher("list_category.jsp");
		dis.forward(request, response);
	}

	public void editCategory() throws ServletException, IOException {
		Integer categoryId=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		Category category=categoryDao.get(categoryId);
		request.setAttribute("category", category);
		RequestDispatcher dis=request.getRequestDispatcher("category_form.jsp");
		dis.forward(request, response);

	}

	public void deleteCategory() throws ServletException, IOException {
		Integer categoryId=Integer.parseInt(request.getParameter("id"));
		categoryDao.delete(categoryId);
		listCategory("Category has been deleted successfully.");

	}
	
}
