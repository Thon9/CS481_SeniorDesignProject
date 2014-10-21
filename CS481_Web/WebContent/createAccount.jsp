<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Account</title>
<style type="text/css">
.center_table {
	position: absolute;
	left: 50%;
	top: 30%;
	margin: -75px 0 0 -135px;
}
input {
	border-radius:15px
}
body {
	background-image: url('film_strip.gif');
    background-color: #cccccc;
}
</style>
</head>
<body>

	<form class="center_table"
		action="${pageContext.servletContext.contextPath}/CreateAccount"
		method="post">
		<h1>Create Account</h1>
		<table>
			<tr>
				<th>First Name:</th>
				<th><input type="text" name="firstName" value="" size="20" /></th>
			</tr>
			<tr>
				<th>Last Name:</th>
				<th><input type="text" name="lastName" value="" size="20" /></th>
			</tr>
			<tr>
				<th>User Name:</th>
				<th><input type="text" name="userName" value="" size="20" /></th>
			</tr>
			<tr>
				<th>Enter Password:</th>
				<th><input type="password" name="password" size="20" /></th>

			</tr>
			<tr>
				<th>Re-Enter Password:</th>
				<th><input type="password" name="passwordConfirm" size="20" /></th>
			</tr>
			<tr>
				<th>E-mail address:</th>
				<th><input type="password" name="email" size="20" /></th>

			</tr>
			<tr>
				<th>Confirm E-mail:</th>
				<th><input type="password" name="emailConfirm" size="20" /></th>
			</tr>
		</table>
		<input type="submit" value="Enter"></input> 
		<a href="login.jsp">Back to Login link</a> 
		<br> ${result}
	</form>

</body>
</html>