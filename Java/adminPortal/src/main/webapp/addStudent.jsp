<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kfh.portal.CheckLogin"%>
<%@ page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Teacher Page</title>
</head>
<body>
	<%
	CheckLogin.checkLogin(session, response);

	String msg = (String) request.getAttribute("message");
	ResultSet result = (ResultSet) request.getAttribute("result");
	ResultSet classes = (ResultSet) request.getAttribute("classes");

	if (classes == null) {
		response.sendRedirect("addStudent");
		return;
	}

	if (msg != null) {
	%>
	<h2 style="color: green;"><%=msg%></h2>
	<%
	}
	%>

	<p>Add a new Student</p>
	<form method="POST" action="addStudent">
		<div>
			<label for="first_name">First Name</label> <input type="text"
				name="first_name" required>
		</div>
		<br>
		<div>
			<label for="last_name">Last Name</label> <input type="text"
				name="last_name" required>
		</div>
		<br>
		<div>
			<label for="class">Class</label> <select name="class">
				<%
				while (classes.next()) {
				%>
				<option value="<%=classes.getInt("id")%>">
					<%=classes.getString("year") + " - " + classes.getString("number")%>
				</option>
				<%
				}
				%>
			</select>
		</div>
		<br>
		<div>
			<input type="submit" value="Add a Student">
		</div>
	</form>

	<br>
	<br>
	<%
	if (result == null)
		return;
	%>
	<table>
		<caption>Students' List</caption>
		<tr>
			<th>Name</th>
			<th>Class</th>
			<th>Delete</th>
		</tr>
		<%
		while (result.next()) {
		%>
		<tr>
			<td><%=result.getString("first_name")%> <%=result.getString("last_name")%></td>
			<td><%=result.getString("year")%> - <%=result.getString("number")%></td>
			<td>
				<form action="deleteStudent" method="post">
					<input type="submit" value="Delete"> <input name="id"
						type="hidden" value="<%=result.getInt("id")%>">
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