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

img {
	width: 100%;
	max-width: 250px;
	max-height: 250px;
	border-radius: 10px;
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
	<h1 align="center">${userName}'s Follow Hashtags</h1>
	<script type="text/javascript">
	</script>

	<!-- logout button -->
	<div style = "float:right">
	<form action="${pageContext.servletContext.contextPath}/Logout"
			method="post">
		<input type="submit" value="Logout" />
	</form>
	</div>
	
	<!-- account button -->
	<div style = "float:left">
		<button type="button" onClick="JavaScript:window.location='Home';">Home</button>
		<button type="button" onClick="JavaScript:window.location='accountinformation.jsp';">Account Information</button>
		<button type="button" onClick="JavaScript:window.location='Gallery';">Gallery</button>
		<button type="button" onClick="JavaScript:window.location='ShowUserGallery';">User Uploads</button>
		<button type="button" onClick="JavaScript:window.location='photo.jsp';">Add Photo</button>
		<button type="button" onClick="JavaScript:window.location='HashTagFollows';">Followed Hashtags</button>
	</div>
	<br>
	<br>
	<center>
	<h2>List of Followed HashTags</h2>
		<c:forEach var="hashTag" items="${hashtagList}">
			<a href="JavaScript:window.location='Gallery?search_object=${hashTag.gethashtagName()}';">
			${hashTag.gethashtagName()}</a>
			<br>
		</c:forEach>
	</center>
</body>
</html>

