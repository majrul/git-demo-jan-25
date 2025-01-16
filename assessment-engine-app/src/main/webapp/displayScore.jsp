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
Integer score = (Integer) session.getAttribute("score");
%>
<h1>Congratulations, you have scored <%= score %> marks</h1>
<%
	session.invalidate();
%>
</body>
</html>