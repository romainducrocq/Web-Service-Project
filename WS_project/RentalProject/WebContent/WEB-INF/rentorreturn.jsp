<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<form action="<%= request.getContextPath() %>/rentorreturn" method="post">
		<input type="hidden" name="employeeid" value=<%= request.getAttribute("employeeid") %>>
		
<% System.out.println("---------- DANS RENTORRETURN.JSP : employeeid ="+request.getAttribute("employeeid")); %>
		

		<input type="radio" name="rentorreturn" value="rent">Rent a vehicle
		
		<input type="radio" name="rentorreturn" value="return">Return a vehicle
		<input type="submit" value="Valider">
		
	</form>


</body>
</html>