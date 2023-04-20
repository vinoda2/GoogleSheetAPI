<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<form action="update" method="Post">
		<div class="col-sm-3 mt-4 p-5 bg-success text-white rounded">
			<c:forEach items="${errors}" var="e">
				<span style="color: red;">${e.message }</span>
			</c:forEach>
			
				<h4>Update here!!</h4>
				<div class="row mb-3">

					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3"
							name="instituteName" placeholder="institute Name"
							value="${dtos.id}" readonly="readonly">
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3"
							name="instituteName" placeholder="institute Name"
							value="${dtos.instituteName}" readonly="readonly">
					</div>
				</div>
				<div class="row mb-3">

					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3"
							name="contactNumber" placeholder="contact Number"
							value="${dtos.contactNumber}">
					</div>
				</div>

				<div class="row mb-3">

					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3"
							name="email" placeholder="E-email">
					</div>
				</div>
				<div class="row mb-3">

					<div class="col-sm-10">
						<input type="password" class="form-control" id="inputPassword3"
							name="password" placeholder="password">
					</div>
				</div>
				<div class="row mb-3">

					<div class="col-sm-10">
						<input type="password" class="form-control" id="inputPassword3"
							name="password" placeholder="password" value=>
					</div>
				</div>
				<div align="center">
					<input type="submit" class="btn btn-secondary" value="update">
				</div>
			</div>
	</div>
	</form>
</body>
</html>