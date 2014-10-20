<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>User Page</title>
</head>
<body>
	<h1>Welcome ${userName} </h1>
	<script type="text/javascript">
	<input type="file" id="upload" name="upload" style="visibility: hidden; width: 1px; height: 1px" multiple />
	<a href="" onclick="document.getElementById('upload').click(); return false">Upload</a>
	</script>
</body>
</html>
