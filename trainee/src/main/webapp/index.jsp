<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>X-workz</title>
<script type="text/javascript">
	function validatePassword() {
		var password = document.getElementById("password").value;
		console.log(password);
		var confirmPassword = document.getElementById("confirm_password").value;
		console.log(confirmPassword)
		if (confirmPassword != password) {
			alert("Entered password is not matching");
			return false;
		}
		return true;
	}
	function validateName() {
		var user=document.getElementById("user");
		var userName=user.value;
		var xhttp = new XMLHttpRequest();
		console.log(userName)
		xhttp.open("GET", "http://localhost:8080/trainee/userName/"+ userName);
		xhttp.send();
		xhttp.onload = function() {
					document.getElementById("displayName").innerHTML = this.responseText;
				}
		document.getElementById('nameError').innerHTML = '';
	}
	function validateEmail(){
		var email=document.getElementById("email");
		var emailId=email.value;
		var xhttp=new XMLHttpRequest();
		console.log(emailId)
		xhttp.open("GET", "http://localhost:8080/trainee/userEmail/"+ emailId);
		xhttp.send();
		xhttp.onload = function() {
					document.getElementById("displayEmail").innerHTML = this.responseText;
				}
		document.getElementById('emailError').innerHTML = '';
		
	}
</script>
</head>
<body>
	<%@ include file="Navbar.jsp"%>
	<%@include file="all_css.jsp"%>
	<div class="container" align="center">
		<h2>Trainee Application</h2>
	</div>
	<div class="container">
		<h1>Registration Form</h1>
		<form action="register" method="post">
			<h5>
				<c:forEach items="${errors}" var="e">
					<span style="color: red;">${e.message }</span>
					<br>
				</c:forEach>
			</h5>
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" id="user" name="traineeName"
					onchange="validateName()" required>
				<span id="nameError" style="color: red"></span> 
				<span id="displayName" style="color: red"></span>
				</td>
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input type="email"
					class="form-control" id="email" name="traineeEmail" onChange="validateEmail()" required>
				<span id="emailError" style="color:red"></span>
				<span id="displayEmail" style="color:red"></span>
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" name="password" required
					pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
					title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
			</div>

			<div class="form-group">
				<label for="confirm_password">Confirm Password:</label> <input
					type="password" class="form-control" id="confirm_password"
					name="confirmPassword" required>
			</div>
			<div id="message">
				<h3>Password must contain the following:</h3>
				<p id="letter" class="invalid">
					A <b>lowercase</b> letter
				</p>
				<p id="capital" class="invalid">
					A <b>capital (uppercase)</b> letter
				</p>
				<p id="number" class="invalid">
					A <b>number</b>
				</p>
				<p id="length" class="invalid">
					Minimum <b>8 characters</b>
				</p>
			</div>

			<button type="submit" class="btn btn-primary"
				onclick="validatePassword()">Register</button>
		</form>
	</div>
</body>
</html>