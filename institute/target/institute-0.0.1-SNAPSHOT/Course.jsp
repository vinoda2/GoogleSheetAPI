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

	<form action="course" method="Post">
		<div class="row mb-3">
			<label for="inputEmail3" class="col-sm-2 col-form-label">Course
				Name</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="inputEmail3"
					name="courseName">
			</div>
		</div>
		<div class="row mb-3">
			<label for="inputEmail3" class="col-sm-2 col-form-label">Course
				type</label>
			<div class="col-sm-10">
				<input type="radio" name="courseType" value="Full Time">Full Time <input
					type="radio" name="courseType" value="Short Time">Short Time
			</div>
		</div>
		<div class="row mb-3">
			<label for="inputEmail3" class="col-sm-2 col-form-label">Course
				Fee</label>
			<div class="col-sm-10">
				<input type="number" class="form-control" id="inputEmail3"
					name="courseFee">
			</div>
		</div>
		<input type="submit" class="btn btn-success" value="submit">
	</form>

</body>
</html>