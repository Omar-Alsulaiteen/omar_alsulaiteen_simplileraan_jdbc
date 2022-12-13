<%@ page import="com.kfh.portal.CheckLogin"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Assign Subject</title>
</head>
<body>

	<%
	
	CheckLogin.checkLogin(session, response);
	ResultSet classes = (ResultSet) request.getAttribute("classes");
	ResultSet subjects = (ResultSet) request.getAttribute("subjects");
	
	if (classes == null || subjects == null) {
		response.sendRedirect("assignSubject");
		return;
	}
	String msg = (String) session.getAttribute("message");
	session.removeAttribute("message");
	
	if (msg != null) {
		%> <h2 style="color:blue;"><%= msg %></h2> <%
	}
	
	%>

	<p>Assign subjects to classes</p>
	<form action="assignSubject" method="POST">
		<label for="subject">Teacher</label> 
		<select name="subject">
			<%
				while(subjects.next()) {
				%>
				<option value="<%= subjects.getInt("id") %>"> <%= subjects.getString("name") %> </option>
				<%
				}
			%>
		</select>

		<label for="class">Class</label>
		<select name="class">
			<%
				while(classes.next()) {
				%>
				<option value="<%= classes.getInt("id") %>"> <%= classes.getString("year") + " - " + classes.getString("number") %> </option>
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