<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>X-workz</title>
</head>
<%@include file="Navbar.jsp"%>
<%@include file="all_css.jsp"%>
<body>
<!--
<div id="demo">
<button type="button" onclick="loadDoc()">getData</button>
</div>

<script>
function loadDoc() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("demo").innerHTML =this.responseText;
      var res=JSON.parse(xhttp.responseText);
      console.log(res);
    
    }
  };
  xhttp.open("GET", "http://localhost:8080/eventorganization/data", true);
  xhttp.send();
}
</script>
-->
</body>
</html>