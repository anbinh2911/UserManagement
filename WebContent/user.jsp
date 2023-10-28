<%@page import="com.app.services.UserService"%>
<%@page import="com.app.models.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List user</title>
</head>
<body>

	<a href="<%= request.getContextPath() %>/new">Create</a>

	<table>
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Address</th>
			<th>Action</th>
		</tr>
		
		<% 	
			List<User> users = (List<User>)request.getAttribute("users");
			for (User u : users) {
		%>
			<tr>
				<td><%= u.getName() %></td>
				<td><%= u.getEmail() %></td>
				<td><%= u.getAddress() %></td>
				<td>
					<a href="<%= request.getContextPath() %>/edit?id=<%= u.getId()%>">Edit</a>
					<a href="<%= request.getContextPath() %>/delete?id=<%=u.getId()%>">Delete</a>
				</td>
			</tr>
		<% 		
			}
		%>
	</table>

</body>
</html>