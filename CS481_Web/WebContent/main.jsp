<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>User Page</title>
<h1 align="center">Welcome ${userName}</h1>
</head>
<body>
	<script type="text/javascript">
	</script>
	
	<form enctype="multipart/form-data" action="${pageContext.servletContext.contextPath}/main" method=post>
	
		
	    <!--input type="text" name="description" /-->
	    <input type="file" name="uploadFile" />
	    <input type="submit" />
	
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
		</table>
	
		<!-- input type="file" id="upload" name="uploadFile" style="visibility: hidden; width: 1px; height: 1px" multiple /> <a href="" onclick = "document.getElementById('upload').click(); return false" > Upload</a>
		<input type="submit" value="Upload Image"></td-->
	</form>
</body>
</html>



<!--  BODY>
	<FORM ENCTYPE="multipart/form-data" ACTION="main.jsp" METHOD=POST>
		<br> <br> <br>
		<table border="0" align="center" bgcolor=#ccFDDEE>
			<tr>

				<BODY>
					<FORM ENCTYPE="multipart/form-data" ACTION="${pageContext.servletContext.contextPath}/AddPhoto" METHOD=POST>
						<br> <br> <br>
						<table border="0" bgcolor=#ccFDDEE>
							<tr>

								<td colspan="2" align="center"><B>UPLOAD THE FILE</B>
									<center></td>
							</tr>
							<tr>
								<td colspan="2" align="center"></td>
							</tr>
							<tr>
								<td><b>Choose the image To Upload:</b></td>
								<td><INPUT NAME="file" TYPE="file"></td>
							</tr>
							<tr>
								<td colspan="2" align="center"></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="submit"
									value="Upload Image"></td>
							</tr>
						</table>
					</FORM>

					${file}
				</BODY>
				</html-->