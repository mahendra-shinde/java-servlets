<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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