<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="shared.IBankDB, java.util.ArrayList, java.util.Arrays, java.rmi.Naming"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Bank DB</h1>
<p>
	Bank name: Current balance: <br/><br/>
	<%
		IBankDB bankDB;
		bankDB = (IBankDB) Naming.lookup("//localhost:2001/BankDB");
		
		ArrayList<String> l = bankDB.getAllBank();
		
		for (String s : l) {
			out.println(bankDB.getbank(s).getId());
			out.println(bankDB.getbank(s).getCurrency());
			out.println(bankDB.getbank(s).getBalance());
			out.println(bankDB.getbank(s).getCardNum());
			out.println(bankDB.getbank(s).getExpiration());
			out.println(bankDB.getbank(s).getCvv());
			out.println(bankDB.getbank(s).getLastName());
			out.println(bankDB.getbank(s).getFirstName());
			%><br/><%
		}
	%>
</p>
<form action="Bank">
	<input type="submit" value="Go to Bank page" />
</form>
</body>
</html>