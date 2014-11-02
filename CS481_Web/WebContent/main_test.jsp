<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	max-width: 300px;
	max-height: 300px;
}

input {
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

<h1 align=center>Welcome ${userName}</h1>
<body>
	<span id="sort_bar"> 
	sort by: 
		<select>
				<option value="date">date</option>
				<option value="views">views</option>
				<option value="likes">likes</option>
		</select>
	</span>

	<span id="search_bar"> search for: <input type="text"
		name="search_object" size="20" /> 
		<select>
			<option value="title">title</option>
			<option value="tags">tags</option>
			<option value="users">users</option>
		</select>
	</span>
	<br><br>
	<a href="test_images/test1.jpg" target="_blank"><img src="test_images/test1.jpg"></a>
	<a href="test_images/test2.jpg" target="_blank"><img src="test_images/test2.jpg"></a>
	<a href="photo.jsp" target="_blank"><img src="test_images/test3.jpg"></a>
	<br>
	<a href="login.jsp">go back</a>
</body>
</html>

