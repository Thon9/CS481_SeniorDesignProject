<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Page</title>
<h1>Welcome ${userName}</h1>
</head>
<BODY>
	<FORM ENCTYPE="multipart/form-data" ACTION="upload.jsp" METHOD=POST>
		<br>
		<br>
		<br>
		<table border="0" bgcolor=#ccFDDEE>
			<tr>

				<td colspan="2" align="center"><B>UPLOAD THE FILE</B>
				<center></td>
			</tr>
			<tr>
				<td colspan="2" align="center"></td>
			</tr>
			<tr>
				<td><b>Choose the file To Upload:</b></td>
				<td><INPUT NAME="file" TYPE="file"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Send File"></td>
			</tr>
			<table>
				</FORM>
</BODY>
</html>

