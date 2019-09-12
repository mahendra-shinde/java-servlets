package com.mahendra;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/index.htm")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private static Logger log = Logger.getLogger(TestServlet.class);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info("Servlet is accesssed via GET method");
		log.info("Preparing the response!");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		if(name==null || name.trim().length()<1) {
			log.warn("The Name parameter was absent!");
			name="Unknown";
		}
		log.error("The random error!");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
