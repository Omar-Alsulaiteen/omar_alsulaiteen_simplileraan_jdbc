<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kfh.portal.CheckLogin"%>
<%@ page import="java.sql.ResultSet"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Class Page</title>
</head>
<body>
	<%
	CheckLogin.checkLogin(session, response);

	String msg = (String) request.getAttribute("message");
	ResultSet result = (ResultSet) request.getAttribute("result");

	if (msg != null) {
	%>
	<h2 style="color: green;"><%=msg%></h2>
	<%
	}
	%>

	<p>Add a new Class</p>
	<form method="POST" action="addClass">
		<div>
			<label for="year">Class Year</label> <input type="number" name="year"
				required>
		</div>
		<br>
		<div>
			<label for="number">Class Number</label> <input type="text"
				name="number" required>
		</div>
		<br>
		<div>
			<input type="submit" value="Add a Class">
		</div>
	</form>
	<br>
	<br>
	<%
	if (result == null)
		return;
	%>
	<table>
		<caption>Classes' List</caption>
		<%
		while (result.next()) {
		%>
		<tr>
			<td><%=result.getString("year")%> - <%=result.getString("number")%>
			<td>
				<form action="deleteClass" method="post">
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