<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Criteria Selection Page</title>
</head>
<%
	String pagebgColor = "#FFEBCD";
%>
<body BGCOLOR="<%=pagebgColor%>">
	<h4>${requestScope["message"]}</h4>
	<p>Please select one of test coverage criteria :</p>
	<form action="MyServlet" method="post">
		<select name="criteria">
			<option value="Edge Coverage">Edge Coverage</option>
			<option value="Edge Pair Coverage">Edge Pair Coverage</option>
			<option value="Prime Path Coverage">Prime Path Coverage</option>
		</select> <br>
		<input type="hidden" id="thisField" name="location" value=${requestScope["location"]}>
		
		<input type="hidden" id="thisField" name="locationOfOutputFile" value=${requestScope["locationtoplace"]}>
		<br> <input type="submit" value = "Submit" />
	</form>
</body>
</html>