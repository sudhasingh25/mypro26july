package com.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;


@WebServlet("")
public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public HomePageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("BooksStoreWebsite");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		CategoryDAO categoryDao=new CategoryDAO(entityManager);
		List<Category> listcategory=categoryDao.listAll();
		request.setAttribute("listcategory", listcategory);
		RequestDispatcher dispatcher=request.getRequestDispatcher("frontend/index.jsp");
		dispatcher.forward(request, response);
	}

}
