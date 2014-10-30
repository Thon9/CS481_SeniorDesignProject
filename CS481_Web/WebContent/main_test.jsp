<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title>User Page</title>
<style type="text/css">
img {
	width: 100%;
	max-width: 300px;
	max-height: 300px;
}
</style>
<h1 align="center">Welcome ${userName}</h1>
</head>

<body>
	sort by:
	<select> 
		<option value="date">date</option>
		<option value="view">views</option>
		<option value="like">likes</option>
	</select>
	<br>
	<img src="test_images/test1.jpg">
	<img src="test_images/test2.jpg">
	<img src="test_images/test3.jpg">
	<br>
	<a href="login.jsp">go back</a>
</body>
</html>

