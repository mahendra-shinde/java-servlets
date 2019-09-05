<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vote for your PET</title>
</head>
<body>
<h2>Vote for your PET</h2>
<form method=post> <!-- action is SELF -->
	VOTE FOR :
	<input type="submit" name="petname" value="Dogs"/>
	<input type="submit" name="petname" value="Cats"/>
</form>
<!-- THIS JSP page sends request to SELF -->
<%
//Attempt to read context attributes if not found reset them to ZERO
	Object dogCount = application.getAttribute("dogCount");
	if(dogCount == null){
		dogCount = new Integer(0);
	}
	Object catCount = application.getAttribute("catCount");
	if(catCount == null){
		catCount = new Integer(0);
	}
//Extract context attributes to primitive ints  
	int dogs = (Integer)dogCount;
	int cats = (Integer)catCount;
//Get request parameter (ie. VOTE for PET)
	String petname = request.getParameter("petname");
//AVOID NullPointer exception by setting value to EMPTY STRING
	if(petname==null)
	{
		petname="";
	}
	
//COUNT the VOTES
		switch(petname){
		case "Dogs":
			dogs++;
			dogCount = dogs;
			break;
		case "Cats":
			cats++;
			catCount = cats;
		}
//Update Vote count in context attributes 
		application.setAttribute("dogCount",dogCount);
		application.setAttribute("catCount",catCount);
	
%>

<h3>Voting Results : Dogs: <%= dogCount %> 
	Cats: <%= catCount %></h3>
</body>
</html>