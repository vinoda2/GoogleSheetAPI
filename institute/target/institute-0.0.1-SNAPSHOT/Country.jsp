<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
function display() {
    var xhttp = new XMLHttpRequest();
    const response=this.responseText;
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
        	console.log("This is Ajax call")
        document.getElementById("countrydropdown").innerHTML =
          	this.responseText;
       }
    };
    xhttp.open("GET", "http://localhost:8080/institute/countries", true);
    alert("controll reached successfully");
    xhttp.send();
}
</script>
</head>
<body>
	<form id="countrydropdown" method="GET">
		<input type="submit" value="Country" onClick="display()"><br>
		Country:<select id="country">
			<option value="">--select--</option>
		</select>

		<!--
<c:forEach var="country" items="${countryMap}">
    Country: ${country.key}  - name: ${country.value}
</c:forEach>-->
	</form>
</body>
</html>