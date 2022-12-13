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
 * Servlet implementation class AddStudent
 */
@WebServlet("/addStudent")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String qry = "select s.id, s.first_name, s.last_name, s.class_id, c.year, c.number from student s"
				+ " inner join class c on s.class_id=c.id";
		String classQuery = "select * from class";
		Database db = (Database) getServletContext().getAttribute("db");
		RequestDispatcher rd = request.getRequestDispatcher("addStudent.jsp");
		
		try {
			ResultSet result = db.runSql(qry);
			ResultSet classes = db.runSql(classQuery);
			
			request.setAttribute("result", result);
			request.setAttribute("classes", classes);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String classID = request.getParameter("class");
		
		if (first_name == null || last_name == null || classID == null) {
			request.setAttribute("message", "Please enter a valid value");
			doGet(request, response);
			return;
		}

		String qry = String.format("insert into student(first_name, last_name, class_id) values ('%s', '%s', %s)", first_name, last_name, classID);

		Statement statement;
		Database db = (Database) getServletContext().getAttribute("db");

//		RequestDispatcher rd = request.getRequestDispatcher("addTeacher.jsp");

		try {
			statement = db.getConnection().createStatement();
			int result = statement.executeUpdate(qry);
			if (result <= 1)
				request.setAttribute("message", "The Student was Assigned Sucessfully");
			else
				request.setAttribute("message", "The Student wasn't Assigned to the database");

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("a Student with the same id already exists");
			request.setAttribute("message", "a Student with the same id already exists");
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("message", "Error assigning Student to the database");
			e.printStackTrace();
		}

		doGet(request, response);

	}

}
