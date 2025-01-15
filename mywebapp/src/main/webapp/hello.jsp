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
	String str = "Welcome to JSP";
%>
<h1><%= str %></h1>
<%
	for(int i=0; i<5; i++) {
%>
		<h2>Hello Everyone!</h2>
<%
	}
%>
</body>
</html>