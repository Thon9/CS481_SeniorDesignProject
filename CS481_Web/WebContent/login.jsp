<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to MyPhotoSpace</title>
</head>
<body>
	<center>
		<form action="${pageContext.servletContext.contextPath}/Login"
			method="post">
			<h1>Welcome to MyPhotoSpace 2</h1>
			<table>
				<tr>
					<th><font size="5">UserName</font></th>
					<th><input type="text" name="userName" size="12" /></th>
				</tr>
				<tr>
					<th><font size="5">Password</font></th>
					<th><input type="password" name="password" size="12" /></th>
				</tr>
			</table>
			<input type="submit" value="Enter"></input> <a
				href="createAccount.jsp">Create Account</a>
		</form>
		<br> ${result}
	</center>

</body>
</html>