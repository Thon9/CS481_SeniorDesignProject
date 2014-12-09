<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
  //autocomplete fuction
  $(function() {
    var availableHashtags = ${autocomplete};
     $( "#hashtags" ).autocomplete({
      source: availableHashtags
    });
  });
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

<h1 align=center>MyPhotoSpace 2 Gallery</h1>

<body>
	<!-- user page selection -->
	<!-- logout button -->
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
	
	<div style = "float:left">
	<!-- Sort tab -->
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
	</div>
	
	
	<!-- Search Bar -->
	<div style = "float:right">
		<form action="${pageContext.servletContext.contextPath}/Gallery?${request.getParameter(search_object)}">
			<span id="search_bar"> 
			
			search for: <input type="text"
				name="search_object" id="hashtags" size="20" /> 
			<input type="submit" value="Enter" ></input>
			</span>
		</form>	
	</div>
	<!-- Follow button -->
	<form action="${pageContext.servletContext.contextPath}/Follow" method="post">
			<input type="submit" value="Follow ${hashTag}"></input>
	</form>
	
	<!-- unfollow button -->
	<form action="${pageContext.servletContext.contextPath}/UnFollow" method="post">
			<input type="submit" value="UnFollow ${hashTag}"></input>
	</form>
	${result}
	<h2 align=center>${hashTag}</h2>
	<br>
	<br>
	<center>
			
	<form enctype="multipart/form-data" action="${pageContext.servletContext.contextPath}/ShowGallery">
		<c:forEach var="photo" items="${photoList}">
					<a href="${photo.editImagePath}" ><img src="${photo.imagePath}"></a>
		</c:forEach>
		
	</form>
	</center>
	<br>
</body>
</html>

