<%@ page import="com.kfh.portal.CheckLogin"%>
<%@ page import="java.sql.ResultSet"%>
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
	
	CheckLogin.checkLogin(session, response);
	ResultSet teachers = (ResultSet) request.getAttribute("teachers");
	ResultSet subjects = (ResultSet) request.getAttribute("subjects");
	if (teachers == null || subjects == null) {
		response.sendRedirect("assignSubjects");
	}

	String msg = (String) session.getAttribute("message");
	session.removeAttribute("message");
	
	if (msg != null) {
		%> <h2 style="color:blue;"><%= msg %></h2> <%
	}
	
	%>

	<form action="assignTeacher" method="POST">
		<label for="teacher">Teacher</label> <select name="teacher">
			<%
				while(teachers.next()) {
				%>
				<option value="<%= teachers.getInt("id") %>"> <%= teachers.getString("first_name") + " " + teachers.getString("last_name") %> </option>
				<%
				}
			%>
		</select>
		<label for="subject">Teacher</label> <select name="subject">
			<%
				while(subjects.next()) {
				%>
				<option value="<%= subjects.getInt("id") %>"> <%= subjects.getString("subject_name") + " Class " + subjects.getString("year") + " - " + subjects.getString("number") %> </option>
				<%
				}
			%>
		</select>

		<div><input type="submit"></div>
	</form>
	<br>
	<br>
	<form action="homePage">
		<input type="submit" value="Go Back" />
	</form>

</body>
</html>