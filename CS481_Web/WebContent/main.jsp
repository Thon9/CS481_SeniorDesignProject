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

<h1 align="center">${userName}</h1>
</head>
<body>
	<script type="text/javascript">
	</script>

	<!--input type="text" name="description" /-->

	<br>

	<center>
		<input type="text" name="hashTag" size="30" /> <input type="submit"
			value="Search for HashTag" /> <br> 
			<a href="Gallery">gallery page</a>

		<form action="${pageContext.servletContext.contextPath}/Logout"
			method="post">
			<input type="submit" value="Logout" />
		</form>
	</center>
	<br>

	<span> </span>
	<!--input type="text" name="description" /-->
	<form enctype="multipart/form-data"
		action="${pageContext.servletContext.contextPath}/main" method=post>

		<input type="file" name="uploadFile" /> <input type="submit" />

		<!-- a href="${pht}" target="_blank"><img src="${pht}" height="200px" width="200px"></a-->

		<table>
			<c:forEach var="photo" items="${photoList}">
				<tr>
					<td><a href="${photo}" target="_blank"><img src="${photo}"
							height="200px" width="200px"></a> <!-- img src=photo height="200px" width="200px" /-->
					</td>
				</tr>
			</c:forEach>
		</table>

		<!-- table border="0" bgcolor=#ccFDDEE>
			<tr>
			<td colspan="2" align="center"><B>UPLOAD THE FILE</B><center></td>
			</tr>
			<tr>
				<td colspan="2" align="center"></td>
			</tr>
			<tr>
				<td><b>Choose the image To Upload:</b></td>
				<td><input name="uploadFile" type="file" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="Upload Image" /></td>
			</tr>
<<<<<<< HEAD
		</table>
	
		<!-- input type="file" id="upload" name="uploadFile" style="visibility: hidden; width: 1px; height: 1px" multiple /> <a href="" onclick = "document.getElementById('upload').click(); return false" > Upload</a>
		<input type="submit" value="Upload Image"></td-->
	</form>
</body>
</html>

