<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="com.mahendra.entities.Product, com.mahendra.daos.ProductDAO, java.util.List" %>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="/FormDemo/resources/style1.css"/>
<title>List of products</title>
</head>
<body>
<%
ProductDAO dao = new ProductDAO();
List<Product>products = dao.getAll();
%>

<h2><%=products.size() %>  Products Found</h2>
<table>
<tr>
	<td>Product ID</td>
	<td>Name</td>
	<td>Quantity</td>
	<td>Rate</td>
</tr>
<%for(Product p: products){ %>
<tr>
<td> <%=p.getProductId() %> </td>
<td><%=p.getName() %></td>
<td><%=p.getQuantity() %></td>
<td><%=p.getRate() %></td>
</tr>
<%} %>
</table>
</body>
</html>