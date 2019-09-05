<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Product Home</title>
</head>
<body>
<h2>List of Products</h2>
<h3>${param.err }</h3>
<c:if test="${requestScope.products ==null }">
	<c:redirect url="index.htm"/>
</c:if>

<c:if test="${requestScope.products !=null }">
<table>
	<tr>
	<td>Product ID</td>
	<td>Name</td>
	<td>Quantity</td>
	<td>Price</td>
	</tr>
	<c:forEach items="${requestScope.products}" var="p">
	<tr>
	<td>${p.productId }</td>
	<td>${p.name }</td>
	<td>${p.quantity }</td>
	<td>${p.rate }</td>
	</tr>
	</c:forEach>
</table>
</c:if>

<form action="add-product" method="post">
Product ID : <input type="number" name="id" /><br/>
Product name: <input type="text" name="pname"/><br/>
Quantity : <input type="number" name="qty" /><br/>
Rate : <input type="number" name="rate"/><br/>	
<input type="submit" value="Save"/>
</form>
</body>
</html>