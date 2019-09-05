package com.mahendra.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mahendra.daos.ProductDAO;
import com.mahendra.entites.Product;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/add-product")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String datafile = getServletContext().getResource("/WEB-INF/data/mydata.csv").getPath();
		
		ProductDAO dao = new ProductDAO(datafile);
		String pid = request.getParameter("id");
		int id = Integer.parseInt(pid);
		String pname = request.getParameter("pname");
		String sqty = request.getParameter("qty");
		int qty = Integer.parseInt(sqty);
		String srate = request.getParameter("rate");
		double rate = Double.parseDouble(srate);
		String msg = null;
		Product product = new Product(id,pname,qty,rate);
		try {
		dao.save(product);
		msg = "Record saved!";
		}catch(Exception ex) {
			msg = "Unable to save record "+ex.getMessage();
		}
		response.sendRedirect("index.htm?err="+msg);
	}

}
