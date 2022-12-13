<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
</head>
<body>
	<p>Homepage</p>
	<%
	String user;
	Object u = session.getAttribute("user");
	if (u == null) {
		response.sendRedirect(response.encodeRedirectURL("index.jsp"));
		return;
	} else {
		user = u.toString();
	}

	String fname = session.getAttribute("first name").toString();
	String lname = session.getAttribute("last name").toString();
	%>
	<p>
		Welcome
		<%=fname%>
		<%=lname%>
	</p>
	<p>You have the following options</p>

	<form action="addSubject">
		<input type="submit" value="Manage subjects" />
	</form>
	<br>
	
	<form action="addTeacher">
		<input type="submit" value="Manage Teachers" />
	</form>
	<br>
	
	<form action="addClass">
		<input type="submit" value="Manage Classes" />
	</form>
	<br>
	
	<form action="addStudent">
		<input type="submit" value="Manage Students" />
	</form>
	<br>
	
	<form action="assignTeacher">
		<input type="submit" value="Assign Teachers" />
	</form>
	<br>
	
	<form action="assignSubject">
		<input type="submit" value="Assign Subjects" />
	</form>
	<br>
	
	<form action="classReport">
		<input type="submit" value="Classes' Reports" />
	</form>
	
	<br>
	<form action="logout">
		<input type="submit" value="Logout" />
	</form>
	


</body>
</html>