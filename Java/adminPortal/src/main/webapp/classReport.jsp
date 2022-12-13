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
	ResultSet classes = (ResultSet) request.getAttribute("classes");
	ResultSet students = (ResultSet) request.getAttribute("students");
	ResultSet report = (ResultSet) request.getAttribute("report");
	if (classes == null) {
		RequestDispatcher rd = request.getRequestDispatcher("classReport");
		rd.forward(request, response);
		return;
	}
	%>

	<form method="GET" action="classReport">
		<label for="cid">Class</label> <select name="cid">
			<%
			while (classes.next()) {
			%>
			<option value="<%=classes.getInt("id")%>"><%=classes.getString("year")%>
				-
				<%=classes.getString("number")%></option>
			<%
			}
			%>
		</select> <input type="submit" value="Select Class">
	</form>

	<%
	if (report == null || students == null)
		return;
	%>

<br><br>
	<table>
		<caption>Subjects</caption>
		<thead>
			<tr>
				<th>Subject &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</th>
				<th>Instructor</th>
			</tr>
		</thead>
		<tbody>
			<%
			while (report.next()) {
			%>
			<tr>
				<td><%=report.getString("name")%></td>
				<td><%=report.getString("first_name")%> <%=report.getString("last_name")%>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

<br><br>
	<table>
		<thead>
			<tr>
				<th>Students</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<%
			while (students.next()) {
			%>
			<tr>
				<td><%=students.getString("first_name")%> <%=students.getString("last_name")%>
			</tr>
			<%
			}
			%>
		</tbody>

	</table>

	<br>
	<br>
	<form action="homePage">
		<input type="submit" value="Go Back" />
	</form>

</body>
</html>