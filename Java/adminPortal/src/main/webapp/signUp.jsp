<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign-up Page</title>
</head>
<body>

	<%
	Object m = session.getAttribute("message");
	if (m != null) {
		String msg = m.toString();
		session.removeAttribute("message");
		out.print("<p>" + msg + "</p>");
	}

	if (session.getAttribute("user") != null) {
		response.sendRedirect("homePage");
	}
	%>

	<form action="signUp" method="POST">
		<div>
			<label for="email">Email</label> <input name="email" type="email"
				required><br>
		</div>
		<label for="first_name">First Name</label> <input name="first_name"
			type="text" required><br>
		<div>
			<label for="last_name">Last Name</label> <input name="last_name"
				type="text" required><br>
		</div>
		<div>
			<label for="password">Password</label> <input name="password"
				type="password" required><br>
		</div>
		<div>
			<input type="submit" value="Sign up">
		</div>

	</form>
</body>
</html>
-->