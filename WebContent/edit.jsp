<%@page import="com.app.services.UserService"%>
<%@page import="com.app.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create user</title>
</head>
<body>

	<%
		String id = request.getParameter("id");
		UserService userService = new UserService();
		User user = userService.find(id);
	%>
	
	<form action="<%= request.getContextPath() %>/update" method="post">
		<input type="text" placeholder="name" name="name" value="<%= user.getName() %>"></br>
		<input type="text" placeholder="email" name="email" value="<%= user.getEmail() %>"></br>
		<input type="text" placeholder="address" name="address" value="<%= user.getAddress() %>"></br>
		<input type="submit" value="update">
	</form>
	
</body>
</html>