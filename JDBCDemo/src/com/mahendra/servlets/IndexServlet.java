package com.mahendra.servlets;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mahendra.daos.ProductDAO;
import com.mahendra.entites.Product;
import com.sun.xml.internal.ws.addressing.ProblemAction;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Resource(name="jdbc/mydb")
	private DataSource ds;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO dao = new ProductDAO(ds);
		List<Product> products = dao.getAll();
		request.setAttribute("products", products);
		RequestDispatcher view = request.getRequestDispatcher("list.jsp");
		view.forward(request, response);
	}

}
