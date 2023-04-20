<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>X-workz</title>
<%@include file="all_css.jsp"%>
</head>
<body>
	<%@include file="Navbar.jsp"%>
	<div align="center">
		<form action="loginpage" method="Post">
	
			<div
				class="col-sm-3 mt-4 p-5 jumbotron bg-success text-white rounded">
				<div>
					<h5>Login Page!!</h5>
					<h5>
						<c:forEach items="${errors}" var="e">
							<span style="color: red;">${e.message }</span>
							<br>
						</c:forEach>
					</h5>

					<div class="row mb-3">

						<div class="col-sm-10">
							<input type="email" class="form-control" id="inputEmail3"
								name="email" placeholder="E-email">
						</div>
					</div>
					<div class="row mb-3">

						<div class="col-sm-10">
							<input type="password" class="form-control" id="inputPassword3"
								name="password" placeholder="Password">
						</div>
					</div>
					<div align="center">
						<input type="submit" class="btn btn-secondary" value="Login">
						<input type="submit" class="btn btn-secondary" value="ForgotPassword">
					</div>
				</div>
			</div>
		</form>
	</div>


</body>
</html>