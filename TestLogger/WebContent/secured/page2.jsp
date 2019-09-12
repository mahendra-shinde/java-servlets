<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Disable browser caching -->
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>Page 1</title>
</head>
<body>
<h2>Page 1</h2>
<h3>Hi ${sessionScope.user}</h3>
<a href="/TestLogger/logout">Logout</a>
</body>
</html>