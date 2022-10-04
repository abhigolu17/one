<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="css/stdQuery.css" rel="stylesheet" ></link>
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<table border="1" style="width:80%">

		<tr>
		<th>Email</th>
		<th>Password</th>
		</tr>
		
		<c:forEach var="item" items="${list}">
			<tr>
				<td>${item.email}</td>
				<td>${item.password}</td>
				<td><a href="${pageContext.request.contextPath }/deleteOne/${item.email }" onclick="return confirm('Are you sure?')">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>