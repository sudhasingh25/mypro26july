package com.bookstore.controller.admin.book;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.controller.admin.service.BookService;

@WebServlet("/admin/list_book")
public class ListBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ListBookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService bookService=new BookService(request, response);
		bookService.listBook();
	}

}
