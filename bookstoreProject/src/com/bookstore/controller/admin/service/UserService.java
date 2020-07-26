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

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserService {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private UserDAO userDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public UserService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		entityManagerFactory=Persistence.createEntityManagerFactory("BooksStoreWebsite");
		entityManager=entityManagerFactory.createEntityManager();
		userDao=new UserDAO(entityManager);
	}
	
	public void listUser() throws ServletException, IOException{
		listUser(null);
	}
	
	public void listUser(String message) throws ServletException, IOException{
		List<Users> listuser=userDao.listAll();
		if(message!=null){
			request.setAttribute("message", message);
		}
		request.setAttribute("listuser", listuser);
		RequestDispatcher dispatcher=request.getRequestDispatcher("list_users.jsp");
		dispatcher.forward(request, response);
	}

	public void createUser() throws ServletException, IOException {
		String email=request.getParameter("email");
		String name=request.getParameter("fullname");
		String password=request.getParameter("password");
		System.out.println("email : "+email+" fullname "+name+" password "+password);
		Users emailExist=userDao.findByEmail(email);
		if(emailExist!=null) {
			String message="Could not create a user with email "+email+" already exist.";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
			
		}else{
			if(emailExist!=null)
			System.out.println("***** User "+ emailExist.getEmail()+" created");
			Users newuser=new Users(email,name,password);
			userDao.create(newuser);
			listUser("User has been created successfully");
		}
		
	}

	public void editUser() throws ServletException, IOException {
		Integer userId=Integer.parseInt(request.getParameter("id"));
		Users user=userDao.get(userId);
		
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("user_form.jsp");
		dispatcher.forward(request, response);
	}

	public void updateUser() throws ServletException, IOException {
		Integer userId=Integer.parseInt(request.getParameter("userid"));
		String email=request.getParameter("email");
		String fullname=request.getParameter("fullname");
		String password=request.getParameter("password");
		Users userById=userDao.get(userId);
		Users userByMail=userDao.findByEmail(email);
		if(userByMail != null && userByMail.getUserId()!=userById.getUserId()){
			String message="User with email "+email+" already exist.";
			request.setAttribute("message", message);
			RequestDispatcher dis=request.getRequestDispatcher("message.jsp");
			dis.forward(request, response);
		}else{
			Users newUser=new Users(userId,email,fullname,password);
			userDao.update(newUser);
			listUser("User have been updated successfully");
		}
	}

	public void deleteUser() throws ServletException, IOException {
		Integer userId=Integer.parseInt(request.getParameter("id"));
		userDao.delete(userId);
		listUser("User have been deleted successfully");
	}

	public void userLogin() throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		boolean loginResult=userDao.checkLogin(email, password);
		if(loginResult){
			System.out.println("user id authenticated");
			request.getSession().setAttribute("useremail", email);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		}else{
			String message="Login Failed";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
	
}
