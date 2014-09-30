<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Account</title>
</head>
<body>
	<table>
			
			<tr>
				<th>First Name: </th>
				<th><input type="text" name="firstName" value="" size="12" /></th>
				
				<th>Last Name: </th>
				<th><input type="text" name="lastName" value="" size="12" /></th>
			</tr>
			<tr>
				<th>User Name: </th>
				<th><input type="text" name="userName" value="" size="12" /></th>
			</tr>
			<tr>
				<th>Enter Password: </th>
				<th><input type="password" name="password" size="12" /></th>
				
			</tr>
			<tr>
				<th>Re-Enter Password: </th>
				<th><input type="password" name="passwordConfirm" size="12" /></th>
			</tr>
			<tr>
				<th>E-mail address: </th>
				<th><input type="password" name="email" size="12" /></th>
				
			</tr>
			<tr>
				<th>Confirm E-mail: </th>
				<th><input type="password" name="emailConfirm" size="12" /></th>
			</tr>
	</table>
	<br>
	<a href="login.jsp">Back to Login link</a>
</body>
</html>