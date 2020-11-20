<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List, java.util.Arrays, com.currencysystem.webservices.currencyserver.*;"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>
	Bank page
</p>
<p>
	<form action="DB">
		<input type="submit" value="Show all available bank accounts" />
	</form>
	<br/><br/>
</p>
<form action="Converter"> 
	<table>
		<tr>
			<td>Bank id: </td>
			<td><input type="text" name="bankid"></td>
		</tr>
	</table>
	<table>
		<tr>			
			<td>Card number: </td>
			<td><input type="number" name="cb_num"></td>
		</tr>
	</table>	
	<table>
		<tr>		
			<td>Expiration date (MM/YY): </td>
			<td><input type="text" name="exp"></td>
		</tr>
	</table>	
	<table>
		<tr>		
			<td>CVV: </td>
			<td><input type="number" name="cvv"></td>
		</tr>
	</table>	
	<table>
		<tr>		
			<td>First name: </td>
			<td><input type="text" name="f_n"></td>
			</tr>
	</table>	
	<table>
		<tr>	
			<td>Last name: </td>
			<td><input type="text" name="l_n"></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>Amount: </td>
			<td><input type="number" name="amount"></td>
		</tr>
	</table>
	
	<input type="submit" value="Withdraw"/>
</form>

</body>
</html>
