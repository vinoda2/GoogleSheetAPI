<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajax-API</title>
<script type="text/javascript">
function restCall(){
	const xHttp=new XMLHttpRequest();
	xHttp.open("GET","http://localhost:8080/institute/statelist");
	xHttp.send();
	var string=JSON.parse(this.responseText);
	
	document.getElementById("getData").innerHTML =this.responseText;
	console.log(this.responseText);
	console.log(string);
}

</script>
</head>
<body>
<div >
<h2>The XMLHttpRequest Object</h2>
<button type="button" onclick="restCall()" id="getData"> Rest API call</button>
</div>
<div id="state">
<h1>State list:</h1>
</div>


</body>
</html>