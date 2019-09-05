# MVC Demo

1.  Create new dynamic web project 'mvcdemo'
2.  Create new HTML page inside `WebContent` directory.
    file name `index.htm`

    ```HTML
    <!DOCTYPE html>
    <html>
    <head>
    <meta charset="UTF-8">
    <title>Search your result!</title>
    </head>
    <body>
    <h2>XYZ University</h2>
    <h3>Result Page</h3>
    <form action="check-result" method="get">
        Roll number: <input type="text" name="rollno"/>
        <input type="submit" value="search" />
    </form>
    </body>
    </html>
    ```
3.  Create new java class `com.mahendra.entities.Result` as model class.

    ```java
    public class Result {
	private int rollNo;
	private String name;
	private String result;
	
	
	public Result(int rollNo, String name, String result) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.result = result;
	}

	public Result() {
		super();
	}
    //GETTERS and SETTERS here...
    ```
4.  Create new service class `com.mahendra.services.ResultService`

    ```java
    package com.mahendra.services;

    import java.util.*;
    import com.mahendra.entities.Result;


    public class ResultService {
    	private static ResultService service = new ResultService();
	public static ResultService getResultService() {
		return service;
	}
	
        private List<Result> results= new LinkedList<>();
        
        private ResultService() {
            results.add(new Result(101,"Durgesh","Failed")); 
            results.add(new Result(102,"Rahul","Failed"));
            results.add(new Result(103,"Rohan","Failed"));
            results.add(new Result(104,"Shahrukh","Pass"));
            results.add(new Result(105,"Shahista","Absent"));
        }
        
        /**
        * Find result by roll number
        * @param rollNo
        * @return result object
        */
        public Result find(int rollNo) {
            for(Result rs : results) {
                if(rs.getRollNo()==rollNo) {
                    return rs;
                }
            }
            return null;
        }
    }
    ```

5.  Create a new servlet 

    ```yaml
    Servlet-Classname:  FindResultServlet
    Package-Name:       com.mahendra.servlets
    URL-Mapping:        /check-result
    ```

6.  Add following lines inside `doGet` method of your servlet:

    ```java
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
    ```
7.  Create new JSP file in `WebContent` directory.

    ```jsp
    <%@ page language="java" contentType="text/html;charset=UTF-8"      pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
    <%@ page import="com.mahendra.entities.Result" %>
    <meta charset="UTF-8">
    <title>The Result page!</title>
    </head>
    <body>
    <h3><%=request.getAttribute("msg") %></h3>
    <%
    Object obj = request.getAttribute("result"); 
    if(obj!=null){
        Result result = (Result)obj;
        out.println("Hey "+result.getName()+" your result is <b>"+result.getResult()+"</b>");
    } %>
    </body>
    </html>
    ```

8.  Go back to `index.htm` file and run application on server.