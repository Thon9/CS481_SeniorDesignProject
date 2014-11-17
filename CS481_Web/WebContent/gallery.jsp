<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
function selectedValue(){
	var value =<%=request.getParameter("selectedValue")%>;
		if (value != null) {
			document.f1.sort_type.selectedIndex = value;
		}
	}
</script>

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

input {
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
</style>
</head>

<h1 align=center>MyPhotoSpace 2 Gallery</h1>
<body>
	<center>
	<a href="main.jsp">User Page</a>
	</center>
	<form action="${pageContext.servletContext.contextPath}/Sort_Gallery"
		method="post">
		<span id="sort_bar"> 
		<select name = "sort_type" >
				<option value="default">sort by </option>
				<option value="date">date</option>
				<option value="views">views</option>
				<option value="likes">likes</option>
				<option value="random">random</option>
		</select> 
		<input type="submit" name="sort"></input>
		</span>
	</form>
	<form action="${pageContext.servletContext.contextPath}/Logout"
		method="post">
		<input type="submit" value="Logout"/>
	</form>

	<form action="${pageContext.servletContext.contextPath}/Search_Gallery"
		method="post">
		<span id="search_bar"> search for: <input type="text"
			name="search_object" size="20" /> 
		<select name = "search_type">
				<option value="id">id</option>
				<option value="title">title</option>
				<option value="tags">tags</option>
				<option value="users">users</option>
		</select>
		<input type="submit" value="Enter" ></input>
		</span>
		<center>${result}</center>
	</form>
	
	<br>
	<br>
	<center>
	<!--
		<a href="test_images/test1.jpg" target="_blank"><img
			src="test_images/test1.jpg"></a> <a href="test_images/test2.jpg"
			target="_blank"><img src="test_images/test2.jpg"></a> <a
			href="photo.jsp" target="_blank"><img src="test_images/test3.jpg"></a>
		<a href="test_images/test1.jpg" target="_blank"><img
			src="test_images/test1.jpg"></a> <a href="test_images/test2.jpg"
			target="_blank"><img src="test_images/test2.jpg"></a>
	/-->	
			
	<form enctype="multipart/form-data" action="${pageContext.servletContext.contextPath}/ShowGallery">
		<c:forEach var="photo" items="${photoList}">
					<a href="${photo}" target="_blank"><img src="${photo}"></a>
					<!-- img src=photo height="200px" width="200px" /-->
		</c:forEach>
		
	</form>
	</center>
	<br>
</body>
</html>

