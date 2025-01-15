<%@page import="com.training.data.Product"%>
<%@page import="java.util.List"%>
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
	int cursor = ((Integer) session.getAttribute("cursor"));
	int pageSize = (Integer) session.getAttribute("pageSize");
	int total = (Integer) session.getAttribute("total");
%>
<h3>Displaying <%= cursor+1 %> to <%= cursor + pageSize %> of <%= total %></h3>
<%
	List<Product> list = (List<Product>) session.getAttribute("listOfProducts");
	for(Product p : list) {
%>
		ID : <%= p.getId() %> <br />
		Name : <%= p.getName() %> <br />
		Price : <%= p.getPrice() %> <br />
		Quantity : <%= p.getQuantity() %> <br />
		<hr />
<%
	}
%>

<a href="ProductServlet?action=prev">&lt;&lt;</a> | 
<a href="ProductServlet?action=next">&gt;&gt;</a>

</body>
</html>