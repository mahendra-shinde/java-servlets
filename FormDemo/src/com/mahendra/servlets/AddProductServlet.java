package com.mahendra.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mahendra.daos.ProductDAO;
import com.mahendra.entities.Product;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/add-product.do")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		//Request parameter could be GET or POST parameter
		// All Request parameters are ALWAYS treated as STRINGS
		String pid = request.getParameter("id");
		int id = Integer.parseInt(pid);
		String pname = request.getParameter("pname");
		String sqty = request.getParameter("qty");
		int qty = Integer.parseInt(sqty);
		String srate = request.getParameter("rate");
		double rate = Double.parseDouble(srate);
		out.println("Your request has been accepted!");
		ProductDAO dao = new ProductDAO();
		try {
		dao.save(new Product(id,pname, rate, qty));
		out.println("Record saved!");
		}catch(RuntimeException ex) {
			out.println("Record failed to save \n"+ex.getMessage());
			ex.printStackTrace();
		}
		out.print("Name: "+pname+", quantity: "+qty+" rate: "+rate);
		out.close();
	}
	
	

}
