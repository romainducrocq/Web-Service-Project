<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/return" method="post">
	<table>
		<tr>
			<td>Vehicle identifier :</td>
			<td><input type="text" name="vehicleid"></td>
			<td>Note (between 0 and 10) :</td>
			<td><input type="text" name="note"></td>
			<td>Condition of return :</td>
			<td><input type="text" name="condition"></td>
		</tr>
		<td colspan="2"><input type="submit" value="Return"></td>
	</table>
	</form>
</body>
</html>