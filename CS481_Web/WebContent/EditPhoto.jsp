<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Edit Photo</title>
<style type="text/css">
body {
	background-size: 100%;
	background-color: #cccccc;
}

img {
	width: 100%;
	max-width: 500px;
	max-height: 500px;
}

input {
	border-radius: 15px;
}

button {
	border-radius: 15px;
}

select {
	border-radius: 15px;
}

span#sort_bar {
	float: left;
}

span#search_bar {
	float: right;
}
form {
    display: inline;
}
</style>
</head>
<h1 align="center">Edit Photo</h1>
<body>
	<div style = "float:right">
	<form action="${pageContext.servletContext.contextPath}/Logout"
			method="post">
		<input type="submit" value="Logout" />
	</form>
	</div>
	
	<!-- account and home button -->
	<div style = "float:left">
	<form action="${pageContext.servletContext.contextPath}/Home"
			method="get">
		<input type="submit" value="Home" />
	</form><form action="${pageContext.servletContext.contextPath}/accountinformation.jsp"
			method="post">
		<input type="submit" value="Account Information" />
	</form><form action="${pageContext.servletContext.contextPath}/Gallery"
			method="get">
		<input type="submit" value="Gallery" />
	</form><form action="${pageContext.servletContext.contextPath}/ShowUserGallery"
			method="get">
		<input type="submit" value="User Uploads" />
	</form><form action="${pageContext.servletContext.contextPath}/photo.jsp"
			method="post">
		<input type="submit" value="Add Photo" />
	</form><form action="${pageContext.servletContext.contextPath}/HashTagFollows"
			method="post">
		<input type="submit" value="Followed Hashtags" />
	</form>
		<!-- button type="button" onClick="JavaScript:window.location='Home';">Home</button>
		<button type="button" onClick="JavaScript:window.location='accountinformation.jsp';">Account Information</button>
		<button type="button" onClick="JavaScript:window.location='Gallery';">Gallery</button>
		<button type="button" onClick="JavaScript:window.location='ShowUserGallery';">User Uploads</button>
		<button type="button" onClick="JavaScript:window.location='photo.jsp';">Add Photo</button
		<button type="button" onClick="JavaScript:window.location='HashTagFollows';">Followed Hashtags</button>-->
	</div>
	<br>
	<br>
	
	<table>
		<tr>
			<td>
				<!-- image column -->
				<a href="${pageContext.servletContext.contextPath}/${photo}" target="_blank"><img src="${pageContext.servletContext.contextPath}/${photo}"></a>
			</td>
			
		</tr>
		<tr>
			<td>
				<!-- hashtag column -->
				
					<c:forEach var="tag" items="${tagList}">
					
						<form action="${pageContext.servletContext.contextPath}/removeTag/${pID}/${tag.hashtagID}" method=post>
							<table>
							<tr>
								<td><center><p>"${tag.hashtagName}"</p></center></td>
								<td><center><input type="submit" value="Delete"/></center></td>
							</tr>
							</table>
						</form>
							
					</c:forEach>
					 
				
			</td>
		</tr>
	</table>
	
	<br>
	<br>
	<center>
	<form action="${pageContext.servletContext.contextPath}/editPhoto/${pID}" method=post>
			<p>Enter Hashtag(s) to add: <input type="text" name="hashTags" value="#"/><i>(Separate Hashtags by # {e.g. #cats #fluffy...})</i></p>
		<br><br>
			<input type="submit" />
	</form>
	</center>
	
	
</body>
</html>