package com.mahendra.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mahendra.entities.Result;
import com.mahendra.services.ResultService;

/**
 * Servlet implementation class FindResultServlet
 */
@WebServlet("/check-result")
public class FindResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Singleton instance of ResultService
		ResultService service = ResultService.getResultService();
		String rollno = request.getParameter("rollno");
		if(rollno==null || rollno.trim().length()<1) {
			System.out.println("No rollno found, redirecting back to HTML...");
			response.sendRedirect("index.htm");		
	        return; //fixing a BUG that prevents next lines to work	 (Server ERROR 500, response closed!)
		}
		int rollNo = 101;
		try {
			rollNo = Integer.parseInt(rollno);
			Result result = service.find(rollNo);
			String msg = "result found!";
			if(result == null) {
				msg = "Results not available for rollnumber "+rollNo;
			}else {
				request.setAttribute("result", result);
			}
			request.setAttribute("msg", msg);
			
			RequestDispatcher view=request.getRequestDispatcher("result.jsp");
			view.forward(request, response);
			
		}catch(NumberFormatException ex) {
			System.out.println("Invalid rollno, redirecting back to HTML...");
			response.sendRedirect("index.htm");
	       
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
