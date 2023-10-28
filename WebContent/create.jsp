<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create user</title>
</head>
<body>
	
	<form action="<%= request.getContextPath() %>/create" method="post">
		<input type="text" placeholder="name" name="name"></br>
		<input type="text" placeholder="email" name="email"></br>
		<input type="text" placeholder="address" name="address"></br>
		<input type="submit" value="create">
	</form>
	
</body>
</html>