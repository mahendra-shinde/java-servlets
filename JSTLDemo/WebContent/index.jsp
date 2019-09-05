<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Vote for your PET</title>
</head>
<body>
	<h2>Vote for your PET</h2>
	<form method=post>
		<!-- action is SELF -->
		VOTE FOR : <input type="submit" name="petname" value="Dogs" /> <input
			type="submit" name="petname" value="Cats" />
	</form>
	<!-- THIS JSP page sends request to SELF -->

	<c:if test="${applicationScope.dogCount == null }">
		<c:set var="dogCount" value="0" />
	</c:if>
	<c:if test="${applicationScope.dogCount != null }">
		<c:set var="dogCount" value="${applicationScope.dogCount}" />
	</c:if>

	<c:if test="${applicationScope.catCount == null }">
		<c:set var="catCount" value="0" />
	</c:if>
	<c:if test="${applicationScope.catCount != null }">
		<c:set var="catCount" value="${applicationScope.catCount}" />
	</c:if>

	<c:if test="${param.petname != null }">
		<c:if test='${param.petname eq "Dogs" }'>
			<c:set var="dogCount" value="${dogCount+1}" />
		</c:if>
		<c:if test='${param.petname eq "Cats" }'>
			<c:set var="catCount" value="${catCount+1}" />
		</c:if>
	</c:if>
	<c:set scope="application" var="dogCount" value="${dogCount}" />
	<c:set scope="application" var="catCount" value="${catCount}" />
	${param.petname }
	<h3>Voting Results : Dogs: ${dogCount } Cats: ${catCount }</h3>
</body>
</html>