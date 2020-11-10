<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="shared.IVehicle" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RENTAL PAGE</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/rental" method="post">
	<input type="hidden" name="employeeid" value=<%= request.getParameter("employeeid") %>>
	<% System.out.println("---------- DANS RENTAL.JSP : employeeid ="+request.getParameter("employeeid")); %>
	<table>
		<tr>

		<% List<IVehicle> vehiclesList = (List<IVehicle>)request.getAttribute("vehiclesList");
		Iterator<IVehicle> it = vehiclesList.iterator();
		while (it.hasNext()) {
			IVehicle v = it.next();
			System.out.println(v); %>
			<input type="radio" id=<%=v.getId()%> name="vehicleid" value=<%=v.getId()%>>
			<label for=<%=v.getId()%>><%=v.getMake()+" "+v.getModel()+" "+v.getYear()+" "+v.getAverageNote()%></label><br>
		<% } %>
		</tr>
		<td colspan="2"><input type="submit" value="Rent"></td>
	</table>
</form>

</body>
</html>