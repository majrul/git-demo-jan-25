<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String agent = request.getHeader("user-agent");
%>
<h2>You are using : <%= agent %></h2>

<%
	response.setHeader("refresh", "5;url=https://www.google.com");
%>
</body>
</html>