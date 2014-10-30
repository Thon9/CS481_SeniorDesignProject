<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to MyPhotoSpace</title>
<style type="text/css">
.center_table {
	width:270px;
	position:absolute;
	left:50%;
	top:30%;
	margin:-75px 0 0 -135px;
}
input {
	border-radius:15px
}
body {
	background-image: url('film.jpg');
	background-size: 100%;
    background-color: #cccccc;
}
h1 {
	color:blue;
}
</style>
</head>
<body>
	<center>
	<h1>Welcome to MyPhotoSpace 2</h1>
		<form class="center_table" action="${pageContext.servletContext.contextPath}/Login"
			method="post">
			<h2>Login</h2>
			<table>
				<tr>
					<th><font size="4">UserName</font></th>
					<th><input type="text" name="userName" size="20"/></th>
				</tr>
				<tr>
					<th><font size="4">Password</font></th>
					<th><input type="password" name="password" size="20" /></th>
				</tr>
			</table>
			<input type="submit" value="Enter" ></input> 
			<a href="createAccount.jsp">Create Account</a>
			<a href="main_test.jsp">image test page</a>
			<br> ${result}
		</form>
	</center>

</body>
</html>