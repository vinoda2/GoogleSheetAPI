<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>X-workz</title>
<script type="text/javascript">
	function validateName() {
		var user = document.getElementById("username");
		var userName = user.value;
		console.log(userName);
		if (userName != null) {
			document.getElementById('nameError').innerHTML = '';
		} else {
			document.getElementById('nameError').innerHTML = 'Plese enter valide name min 4 and max 30 character';
		}
	}
</script>
</head>
<body>
	<%@ include file="Navbar.jsp"%>
	<%@include file="all_css.jsp"%>
	<div class="container">
		<h1>Login Here!!</h1>
		<h5 style="color: red">${message}</h5>
		<h5 style="color: red">${messages}</h5>
		<h5 style="color: red">${match}</h5>
		<form action="login" method="post">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" id="username" name="traineeName" required
					onchange="validateName()"> <span id="nameError"
					style="color: red"></span> <span id="displayUserName"
					style="color: red"></span>
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" name="password" required>
			</div>
			<button type="submit" class="btn btn-primary">Login</button>
		</form>
		<br> <a href="index.jsp">Sign-Up</a><br> <a
			href="ResetPassword.jsp">reset password</a>
	</div>


</body>
</html>