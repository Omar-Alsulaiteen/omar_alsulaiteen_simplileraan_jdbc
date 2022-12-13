package com.kfh.portal;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AssignTeacher
 */
@WebServlet("/assignTeacher")
public class AssignTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResultSet subjects;
		ResultSet teachers;
		Database db = (Database) getServletContext().getAttribute("db");
		RequestDispatcher rd = request.getRequestDispatcher("assignTeacher.jsp");
		String qry = "select sc.id, s.name as subject_name, c.year, c.number"
				+ " from class_subject sc"
				+ "  inner join subject s on s.id = sc.subject_id"
				+ "  inner join class c on c.id = sc.class_id"
				+ "    order by s.name asc;";
		
		String qry2 = "select * from instructor;";
		
		try {
			subjects = db.runSql(qry);
			teachers = db.runSql(qry2);
			
			request.setAttribute("subjects", subjects);
			request.setAttribute("teachers", teachers);

			rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error retriving the subject's or teacher's list");
		}
	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("subject");
		String t = request.getParameter("teacher");
		
		if (s == null || t == null) {
			doGet(request, response);
			return;
		}
		int subjectClassID = Integer.parseInt(s);
		int teacherID = Integer.parseInt(t);
		
		String qry = String.format("update class_subject set instructor_id= %d where id=%d", teacherID, subjectClassID);
		Database db = (Database) getServletContext().getAttribute("db");

		Statement statement;
		HttpSession session = request.getSession();

		try {
			statement = db.getConnection().createStatement();
			int result = statement.executeUpdate(qry);
			if (result <= 1)
				session.setAttribute("message", "Teacher Assigned Sucessfully");
			else
				session.setAttribute("message", "Teacher wasn't Assigned to the database");
			
		} catch (SQLException e) {
			session.setAttribute("message", "Error assigning teacher to the database");
			e.printStackTrace();
		}
		
		doGet(request, response);

		
	}

}
