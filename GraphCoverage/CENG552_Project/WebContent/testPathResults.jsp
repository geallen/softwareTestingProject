<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result Page</title>
</head>
<%
	String pagebgColor = "#FFEBCD";
%>
<body BGCOLOR="<%=pagebgColor%>">
	<p>You can also find your resulted paths in excel in location ${message}result.xlsx</p>
	<p>According to ${criteria}, your Test Paths are : </p>
	<p>${testPaths}</p>

</body>
</html>