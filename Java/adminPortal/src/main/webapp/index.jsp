<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Welcome to Admin's Portal
	
	<%
		String m = (String) request.getAttribute("message");
		if (m != null) {
			out.print("<p style=\"color:red;\">" + m + "</p>");
		}
		
		if (session.getAttribute("user") != null) {
			response.sendRedirect("homePage");
		}
	%>
	<p>Login</p>
	<form action="login" method="post">
		<div>
			<label for="email">Email</label> <input name="email" type="email" required><br>
		</div>
		<br>
		<div>
			<label for="password">Password</label> <input name="password" type="password"
				required><br>
		</div>
		<br>
		<div>
			<input type="submit" value="Login">
		</div>
	</form>
	
	<!--  <p>You do not have an account? <a href="signUp.jsp">Sign up</a> </p> -->
</body>
</html>