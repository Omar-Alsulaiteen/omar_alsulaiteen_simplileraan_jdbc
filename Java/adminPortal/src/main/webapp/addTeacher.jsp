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

	if (msg != null) {
	%>
	<h2 style="color: green;"><%=msg%></h2>
	<%
	}
	%>

	<p>Add a new Teacher</p>
	<form method="POST" action="addTeacher">
		<div>
			<label for="first_name">First Name</label> 
			<input type="text" name="first_name" required>
		</div>
		<br>
		<div>
			<label for="last_name">Last Name</label> 
			<input type="text" name="last_name" required>
		</div>
		<br>
		<div>
			<label for="degree">Degree</label> 
			<input type="text" name="degree" required>
		</div>
		<br>
		<div>
			<label for="major">Major</label> 
			<input type="text" name="major" required>
		</div>
		<br>
		<div>
			<input type="submit" value="Add a Teacher">
		</div>
	</form>
	
		<br>
	<br>
	<%
	if (result == null)
		return;
	%>
	<table>
		<caption>Teachers' List</caption>
		<%
		while (result.next()) {
		%>
		<tr>
			<td><%=result.getString("first_name")%>  <%=result.getString("last_name")%>
			<td>
				<form action="deleteTeacher" method="post">
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