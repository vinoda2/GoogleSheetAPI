<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>X-workz</title>
<script type="text/javascript">

function validateName(){
	var name=document.getElemetById("username");
	var userName=name.value;
	console.log(userName);
}
</script>
</head>
<body>
	<%@ include file="Navbar.jsp"%>
	<%@include file="all_css.jsp"%>
	<h5 style="color: red">${message}</h5>
	<div class="container">
		<h1>Login Here!!</h1>
		<form action="login" method="post">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" id="username" name="traineeName" required onchange="validateName()">
					<span id="nameError" style="color: red"></span> 
			 <span id="displayUserName" style="color: red"></span>
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" name="password" required>
			</div>
			 <button type="submit" class="btn btn-primary">Login</button>
		</form>
	</div>


</body>
</html>