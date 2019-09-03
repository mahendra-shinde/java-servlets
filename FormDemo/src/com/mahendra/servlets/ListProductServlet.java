package com.mahendra.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mahendra.daos.ProductDAO;
import com.mahendra.entities.Product;

/**
 * Servlet implementation class ListProductServlet
 */
@WebServlet("/list-products.do")
public class ListProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductDAO dao = new ProductDAO();
		List<Product>products = dao.getAll();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>List of products</title></head>");
		out.println("<body><h3>Found "+products.size()+" products</h3>");
		out.println("<table><tr><td>Product ID</td><td>Name</td><td>Quantity</td><td>Rate</td></tr>");
		
		for(Product p : products) {
			out.println("<tr><td>"+p.getProductId());
			out.println("</td><td>"+p.getName());
			out.println("</td><td>"+p.getQuantity());
			out.println("</td><td>"+p.getRate());
		}
		out.println("</tr></table>");
		
		out.close();
	}

}
