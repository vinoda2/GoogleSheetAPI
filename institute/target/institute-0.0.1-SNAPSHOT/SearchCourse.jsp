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
<h3 ><span style="color: red;">${message}</span></h3>
<form action="searchId" method="Post">
	Search By Id <input type="text" name="id"/>
	<input type="submit" value="search" class="btn btn-primary"/>
	
	<div>
			<h3 style="color: Green;">Search Results</h3>
			<table class="table">
			
				<thead class="table-dark">
					<tr class="table-primary">
					<th>Id</th>
						<th>Institute Name</th>
						<th>E-mail</th>
						<th>Contact Number</th>
						
					</tr>
				</thead>
				<tbody>
						<tr>
							<td>${dtos.id}</td>
							<td>${dtos.instituteName}</td>
							<td>${dtos.email}</td>
							<td>${dtos.contactNumber}</td>
							</tr>
					</tbody>
					</table>
			</div>
			
					
</form>
</body>
</html>