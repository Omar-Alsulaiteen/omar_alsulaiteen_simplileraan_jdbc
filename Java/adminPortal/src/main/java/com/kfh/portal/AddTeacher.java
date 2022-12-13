package com.kfh.portal;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddTeacher
 */
@WebServlet("/addTeacher")
public class AddTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String qry = "select * from instructor";
		Database db = (Database) getServletContext().getAttribute("db");
		RequestDispatcher rd = request.getRequestDispatcher("addTeacher.jsp");
		
		try {
			ResultSet result = db.runSql(qry);
			request.setAttribute("result", result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String degree = request.getParameter("degree");
		String major = request.getParameter("major");
		
		if (first_name == null || last_name == null || degree == null || major == null) {
			request.setAttribute("message", "Please enter a valid value");
			doGet(request, response);
			return;
		}

		String qry = String.format("insert into instructor(first_name, last_name, degree, major) values ('%s', '%s', '%s', '%s')", first_name, last_name, degree, major);

		Statement statement;
		Database db = (Database) getServletContext().getAttribute("db");

//		RequestDispatcher rd = request.getRequestDispatcher("addTeacher.jsp");

		try {
			statement = db.getConnection().createStatement();
			int result = statement.executeUpdate(qry);
			if (result <= 1)
				request.setAttribute("message", "The teacher was Assigned Sucessfully");
			else
				request.setAttribute("message", "The teacher wasn't Assigned to the database");

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("a teacher with the same id already exists");
			request.setAttribute("message", "a Teacher with the same id already exists");
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("message", "Error assigning Teacher to the database");
			e.printStackTrace();
		}
		
		doGet(request, response);

	}

}
