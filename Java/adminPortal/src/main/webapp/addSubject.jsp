<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kfh.portal.CheckLogin"%>
<%@ page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Subject Page</title>
</head>
<body>
	<%
	CheckLogin.checkLogin(session, response);

	ResultSet result = (ResultSet) request.getAttribute("result");
	String msg = (String) request.getAttribute("message");
//	request.removeAttribute("message");

	if (msg != null) {
	%>
	<h2 style="color: green;"><%=msg%></h2>
	<%
	}
	%>

	<form method="POST" action="addSubject">
		<div>
			<label for="subject">Subject's Name</label> <input type="text"
				name="name" required>
		</div>
		<div>
			<input type="submit" value="Add a Subject">
		</div>
	</form>
			<br>
	<br>
	<%
	if (result == null)
		return;
	%>
	<table>
		<caption>Subjects' List</caption>
		<%
		while (result.next()) {
		%>
		<tr>
			<td><%= result.getString("name") %></td>
			<td>
				<form action="deleteSubject" method="post">
					<input type="submit" value="Delete">
					<input name="id" type="hidden" value="<%= result.getInt("id") %>">
				</form>
			</td>
		</tr>

		<%
		}
		%>

	</table>
	<br>
	<br>
	<form action="homePage">
		<input type="submit" value="Go Back" />
	</form>
	
</body>
</html>