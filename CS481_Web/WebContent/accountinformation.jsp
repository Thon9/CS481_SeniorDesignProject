<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Settings</title>
<style type="text/css">
body {
	background-size: 100%;
	background-color: #cccccc;
}

button {
	border-radius: 15px;
}

input {
	border-radius: 15px;
}
</style>

</head>
<body>
	<h1 align="center">Account Setting of ${userName}</h1>

	<!--the top bar include home and logout /-->
	<div style = "float:left">
		<button type="button" onClick="JavaScript:window.location='main.jsp';">Home</button>
	</div>
	<div style = "float:right">
	<form action="${pageContext.servletContext.contextPath}/Logout"
			method="post">
		<input type="submit" value="Logout" />
	</form>
	</div>
	<br><br>
	<!--user infomation /-->
	<script type="text/javascript">
	</script>
	<form action="${pageContext.servletContext.contextPath}/EditUser" method="post">
	<table align = "center">
		<tr>
			<th>First Name:</th>
			<th><input type="text" name="firstname" value="${firstname}" size="20" /></th>
		</tr>
		<tr>
			<th>Last Name:</th>
			<th><input type="text" name="lastname" value="${lastname}" size="20" /></th>
		</tr>
		<tr>
			<th>User Name:</th>
			<th><input type="text" name="userName" value="${userName}" size="20" /></th>
		</tr>
		<tr>
			<th>Enter Password:</th>
			<th><input type="text" name="password" value="${password}" size="20" /></th>

		</tr>
		<tr>
			<th>E-mail address:</th>
			<th><input type="text" name="email" value="${email}" size="20" /></th>
		</tr>
		<tr>
		
		</tr>
	</table>
	<center>
		<!-- edit user button -->
		<button type="submit" >Save Change</button>
	</center>
	</form>
	
	<center>
	<br><br><br><br>
	<!--delete user button -->
	<form action="${pageContext.servletContext.contextPath}/AccountInformation" method="post">
		<button type="submit" >DELETE ACCOUNT</button>
	</form>
	</center>
</body>
</html>