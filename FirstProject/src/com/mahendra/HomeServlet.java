package com.mahendra;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
// URL MApping begins with "/" 
// At Run time it gets Application Context as a prefix
// Application Context = http://localhost:8080/FirstProject
@WebServlet({"/index.html","/index.htm","*.htm"})
public class HomeServlet extends HttpServlet {
	
	private int counter;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("Servlet is ready to die");
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("Congrats, new servlet instance is born!");
		counter = 0;
	}

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<html><body><h2>Hello World</h2>");
		counter ++;
		out.println("<h3>Request #"+ counter+" </body></html>");
		/// Print CLIENT's IP Address
		System.out.println("Got Request from "+request.getRemoteAddr());
		// Print the browser details
		System.out.println(" Browser used was : "+request.getHeader("User-Agent"));
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
