<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajax-API</title>
<script type="text/javascript">
function restCall() {

	let httpRequest = new XMLHttpRequest();
	httpRequest.open("GET","http://localhost:8080/institute/countries",true);
	httpRequest.send();
	httpRequest.onload=function(){
		//alert(httpRequest.reponseText);
	}
	console.log("this is inside rest call")
	}

</script>
<script type="text/javascript">
var obj;
if(window.XMLHttpRequest){
	
	obj = new XMLHttpRequest();
}else{
	obj=new ActiveXObject("Microsoft.XMLHTTP");
}
obj.open("GET","http://localhost:8080/institute/method",true)
obj.send();
obj.onreadycountrychange=function(){
	if(obj==4&&obj==200){
		console.log(obj.response);
		var countryname=JSON.parse(obj.responseText)
		console.log(countryname);
	}
}
</script>
</head>
<body>
<div >
<h2>The XMLHttpRequest Object</h2>
<button type="button" onclick="restCall()">Rest API call</button>
</div>

<select id="country">
<option value="">--select--</option>
</select>
<select id="states">
<option value="">--select--</option>
</select>


</body>
</html>