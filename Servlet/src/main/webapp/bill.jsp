<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% double bill=(Double) request.getAttribute("Bill"); %>
<h1 style='color:green'>Total Bill Amount is: <%= bill %></h1>
</body>
</html>