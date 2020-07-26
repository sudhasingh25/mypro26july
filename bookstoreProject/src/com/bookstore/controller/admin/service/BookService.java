package com.bookstore.controller.admin.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookService {
	private  EntityManager entityManager;
	private  EntityManagerFactory entityManagerFactory;
	private  BookDAO bookDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private CategoryDAO categoryDao;
	public BookService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		entityManagerFactory=Persistence.createEntityManagerFactory("BooksStoreWebsite");
		entityManager=entityManagerFactory.createEntityManager();
		categoryDao=new CategoryDAO(entityManager);
		bookDao=new BookDAO(entityManager);
	}
	
	public void listBook() throws ServletException, IOException{
		listBook(null);
	}
	
	public void listBook(String message) throws ServletException, IOException{
		List<Book> listbooks=bookDao.listAll();
		request.setAttribute("listbooks", listbooks);
		if(message!=null){
			request.setAttribute("message", message);
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("book_list.jsp");
		dispatcher.forward(request, response);
	}

	public void newBook() throws ServletException, IOException {
		List<Category> listCategory=categoryDao.listAll();
		request.setAttribute("listCategory", listCategory);
		RequestDispatcher dispatcher=request.getRequestDispatcher("book_form.jsp");
		dispatcher.forward(request, response);
	}
	
	public void createBook() throws ServletException, IOException {
		int categoryId=Integer.parseInt(request.getParameter("category"));
		String title=request.getParameter("title");
		
		Book existBook=bookDao.findByTitle(title);
		
		if(existBook!=null){
			String message="Could not create a book with name "+title+" already exist.";
			listBook(message);
			return;
			
		}
		
		String author=request.getParameter("author");
		String isbn=request.getParameter("isbn");
		String description=request.getParameter("description");
		float price=Float.parseFloat(request.getParameter("price"));
		
		DateFormat dateFormat= new SimpleDateFormat("mm/dd/yyyy");
		Date publishDate;
		try{
			publishDate=dateFormat.parse(request.getParameter("publishdate"));
		}catch(ParseException ex){
			ex.printStackTrace();
			throw new ServletException("Error parsing publisg date(dateformat mm/dd/yyyy)");
		}
		
		Book newbook=new Book();
		Part part=request.getPart("bookimage");
		
		if(part!=null && part.getSize()>0){
			long size=part.getSize();
			byte[] imageBytes=new byte[(int)size];
			
			InputStream inputStream=part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			System.out.println("***************************************************************************************************"
					+ "**********************************************image********");
			newbook.setImage(imageBytes);
		}
		newbook.setTitle(title);
		newbook.setAuthor(author);
		newbook.setDescription(description);
		newbook.setIsbn(isbn);
		newbook.setPrice(price);
		Category category=categoryDao.get(categoryId);
		newbook.setCategory(category);
		newbook.setPublishDate(publishDate);
		Book createdBook=bookDao.create(newbook);
		
		if(createdBook.getBookId()>0){
			String message="A new book created successfully.";
			request.setAttribute("message", message);
			listBook(message);
		}
	}

	public void editBook() throws ServletException, IOException {
		Integer bookid=Integer.parseInt(request.getParameter("id"));
		Book book=bookDao.get(bookid);
		List<Category> listCategory=categoryDao.listAll();
		request.setAttribute("listCategory",listCategory);

		request.setAttribute("book",book);
		RequestDispatcher dis=request.getRequestDispatcher("book_form.jsp");
		dis.forward(request, response);
	}
	
	public void updateBook() throws ServletException, IOException{
		Integer bookid=Integer.parseInt(request.getParameter("bookid"));
		System.out.println("bookid :  "+bookid);
		
		String title=request.getParameter("title");
		
		Book existBook=bookDao.get(bookid);
		Book bookByTitle=bookDao.findByTitle(title);
		
		if(!existBook.equals(bookByTitle) ){
			String message="Could not update a book with name "+title+" already exist.";
			listBook(message);
			return;
			
		}
		int categoryId=Integer.parseInt(request.getParameter("category"));

		String author=request.getParameter("author");
		String isbn=request.getParameter("isbn");
		String description=request.getParameter("description");
		float price=Float.parseFloat(request.getParameter("price"));
		
		DateFormat dateFormat= new SimpleDateFormat("mm/dd/yyyy");
		Date publishDate;
		try{
			publishDate=dateFormat.parse(request.getParameter("publishdate"));
		}catch(ParseException ex){
			ex.printStackTrace();
			throw new ServletException("Error parsing publisg date(dateformat mm/dd/yyyy)");
		}
		
		Book newbook=new Book();
		Part part=request.getPart("bookimage");
		
		if(part!=null && part.getSize()>0){
			long size=part.getSize();
			byte[] imageBytes=new byte[(int)size];
			
			InputStream inputStream=part.getInputStream();
			inputStream.read(imageBytes);
			inputStream.close();
			System.out.println("***************************************************************************************************"
					+ "**********************************************image********");
			newbook.setImage(imageBytes);
		}
		newbook.setTitle(title);
		newbook.setAuthor(author);
		newbook.setDescription(description);
		newbook.setIsbn(isbn);
		newbook.setPrice(price);
		Category category=categoryDao.get(categoryId);
		newbook.setCategory(category);
		newbook.setPublishDate(publishDate);
		Book updatedBook=bookDao.update(newbook);
		
		if(updatedBook.getBookId()>0){
			String message="A new book created successfully.";
			request.setAttribute("message", message);
			listBook(message);
		}
	}
	
		
}
