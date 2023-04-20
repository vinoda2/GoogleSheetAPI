<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>X-workz</title>
<%@include file="all_css.jsp"%>
<%@include file="Navbar.jsp"%>
</head>
<body>

File: 
<form action="imageupload" method="post" enctype="multipart/form-data">
<input type="file" name="image" size="50" />
<br />
<input type="submit" value="Upload" />
</form>
<form action="display" method="post" >
</form>

<h1>Display Image</h1>
<img src="" height="350" width="175">
</body>
</html>