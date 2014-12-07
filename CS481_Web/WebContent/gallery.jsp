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
  $(function() {
    var availableHashtags = [
       $("autocomplete")
    ];
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
			
			<!-- autocomplete textbox-->
			<!-- ${search_object}
			<script type="text/javascript">
				$(function() { 
					var availItems = ["CATS", "DOGS", "KOALA", 
					                   "BEAR"];
				$("#search_object").autocomplete({
					source: availItems, 
					minLength: 0
				}).focus(function(){
						$(this).trigger('keydown.autocomplete');
					});
				});
			</script>
			 -->
			<input type="submit" value="Enter" ></input>
			</span>
		</form>	
	</div>
	<!-- Follow button -->
	<form action="${pageContext.servletContext.contextPath}/Follow" method="post">
			<input type="submit" value="Follow ${hashTag}"></input>
	</form>
	${result}
	<h2 align=center>${hashTag}</h2>
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

