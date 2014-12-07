<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Edit Photo</title>
</head>
<body>
	<div style = "float:right">
	<form action="${pageContext.servletContext.contextPath}/Logout"
			method="post">
		<input type="submit" value="Logout" />
	</form>
	</div>
	
	<!-- account and home button -->
	<div style = "float:left">
		<button type="button" onClick="JavaScript:window.location='Home';">Home</button>
		<button type="button" onClick="JavaScript:window.location='accountinformation.jsp';">Account Information</button>
		<button type="button" onClick="JavaScript:window.location='Gallery';">Gallery</button>
		<button type="button" onClick="JavaScript:window.location='ShowUserGallery';">User Uploads</button>
		<button type="button" onClick="JavaScript:window.location='photo.jsp';">Add Photo</button>
	</div>
	<br>
	<br>
	
	<table>
		<tr>
			<td>
				<!-- image column -->
				<a href="${pageContext.servletContext.contextPath}/${photo}" target="_blank"><img height="300" width="300" src="${pageContext.servletContext.contextPath}/${photo}"></a>
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
			<p>Enter Hashtag(s) to add: <input type="text" name="hashTags" /><i>(Separate Hashtags by # {e.g. #cats #fluffy...})</i></p>
		<br><br>
			<input type="submit" />
	</form>
	</center>
	
	
</body>
</html>