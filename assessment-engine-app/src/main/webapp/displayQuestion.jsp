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
<form action="CheckAnswerServlet">
<%
Question q = (Question) session.getAttribute("question");
List<Option> ops = q.getOptions();
%>
<h2>Question: <%= q.getText() %></h2>
<%
	int opNo = 0;
	for(Option op : ops) {
%>
	<h3><input type="radio" name="op" value="<%= opNo++ %>" /><%= op.getText() %></h3>
<%
	}
%>
<br />
<!-- <a href="QuestionLoaderServlet">NEXT QUESTION</a>-->
<button type="submit">NEXT QUESTION</button>
</form>
</body>
</html>