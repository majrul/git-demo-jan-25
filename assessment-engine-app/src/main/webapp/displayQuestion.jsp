<%@page import="com.training.datamodel.Option"%>
<%@page import="java.util.List"%>
<%@page import="com.training.datamodel.Question"%>
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
Question q = (Question) session.getAttribute("question");
List<Option> ops = q.getOptions();
%>
<h2>Question: <%= q.getText() %></h2>
<%
	for(Option op : ops) {
%>
	<h3><%= op.getText() %></h3>
<%
	}
%>
<br />
<a href="QuestionLoaderServlet">NEXT QUESTION</a>
</body>
</html>