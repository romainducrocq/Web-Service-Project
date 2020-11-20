<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List, java.util.Arrays, com.currencysystem.webservices.currencyserver.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>
	 
	<% 
		double amountSelected = Double.parseDouble(request.getParameter("amount"));
		double afterConvertion = Double.parseDouble(request.getAttribute("convertion").toString());
		String currencySelected = request.getAttribute("currencyName").toString(); 
		
		if (afterConvertion > 0){ 
		// If the bank account is known
			%> You have been debited of:<%
		out.println(afterConvertion);
	%>
	
	<%
		out.println(currencySelected); 
	%>
	(  
	<%
		out.println(amountSelected);
	%>
	EUR)
	<%} else if (afterConvertion == 0) { %>
		Not enough funds to purchase the car.
	<% } else { %>
		Bank detail incorrect.
	<%} %>
</p>

<form action="Bank">
	<input type="submit" value="Go to Bank page" />
</form>
</body>
</html>