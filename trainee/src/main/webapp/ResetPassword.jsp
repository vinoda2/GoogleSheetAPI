<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>X-workz</title>
</head>
<body>
	<%@ include file="Navbar.jsp"%>
	<%@include file="all_css.jsp"%>
	<div align="center">
		<form action="resetpassword" method="post">
			User Email <br>
			<input type="email" name="email" id="userEmail"
				onchange="validateEmail()"> <br>
			<button type="submit" class="btn btn-success">Reset password</button>
		</form>
		</div>

</body>
</html>