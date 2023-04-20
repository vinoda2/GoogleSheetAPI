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
	<h3>
		<span style="color: red;">${message}</span>
	</h3>
	<form action="searchName" method="Post">
		Search By Name <input type="text" name="instituteName" /> <input
			type="submit" value="search" class="btn btn-primary" />
		<div>
			<h3 style="color: Green;">Search Results</h3>
			<table class="table">
				<thead class="table-dark">
					<tr class="table-primary">
					<th>Id</th>
						<th>Institute Name</th>
						<th>E-mail</th>
						<th>Contact Number</th>
						<th>Update</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${dto}" var="d">
						<tr>
							<td>${d.id}</td>
							<td>${d.instituteName}</td>
							<td>${d.email}</td>
							<td>${d.contactNumber}</td>
							<td><a href="update?id=${d.id}">Edit</a>
								&nbsp;&nbsp;&nbsp;
								 <a href="delete?id=${d.id}">Delete</a>
							</td>
						</tr>
					</c:forEach>
			</table>
		</div>
	</form>
</body>
</html>