<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title>Upload Photo Page</title>

<style type="text/css">
body {
	background-size: 100%;
	background-color: #cccccc;
}
</style>

<h1 align="center">${userName}</h1>
</head>
<body>
	<script type="text/javascript"></script>
	<center>
		<a href="Gallery">gallery page</a>
		<form action="${pageContext.servletContext.contextPath}/Logout"method="post">
			<input type="submit" value="Logout"/>
		</form>
	</center>
	<br>
	<br>
	<form enctype="multipart/form-data"action="${pageContext.servletContext.contextPath}/AddPhoto" method=post>

			<input type="file" name="uploadFile" />
		<br><br>
			<p>Enter Hashtag(s): <input type="text" name="hashTags" /><i>(Separate Hashtags by # {e.g. #cats #fluffy...})</i></p>
		<br><br>
			<input type="submit" />
	</form>
</body>
</html>

