<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title>User Page</title>

<style type="text/css">
body {
	background-size: 100%;
	background-color: #cccccc;
}
</style>


</head>
<body>
	<h1 align="center">Welcome ${userName}</h1>
	
	<script type="text/javascript">
	</script>

	<!--input type="text" name="description" /-->

	<br>

	<center>
		<form action="${pageContext.servletContext.contextPath}/Search_Gallery" method="post">
			<input type="text" name="search_object" size="30" /> <input type="submit"
				value="Search for HashTag" /> 
		</form>
			<br> 
			<a href="Gallery">gallery page</a>
			<br>
			<a href="photo.jsp">Add Photo</a>
			
		<form action="${pageContext.servletContext.contextPath}/Logout"
			method="post">
			<input type="submit" value="Logout" />
		</form>
	</center>
	<br>
<h2>List of Followed HashTags</h2>
<a href="Search_Gallery?hashTag=#undefine">#undefine</a>

</body>
</html>

