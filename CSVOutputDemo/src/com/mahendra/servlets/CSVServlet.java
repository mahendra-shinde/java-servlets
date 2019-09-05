package com.mahendra.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CSVServlet
 */
@WebServlet("/export")
public class CSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CSVServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setHeader( "Content-Disposition", 
				  "attachment;filename=file.csv"	);
		  response.setContentType("text/csv");
		  PrintWriter out = response.getWriter();
		  out.println("E101, Abcd, 12000, Accountant");
		  out.println("E102, Pqrt, 13000, Accountant");
		  out.println("E103, Xyyzd, 15400, Accountant");
		  out.flush();
		  out.close();
	}	

}
