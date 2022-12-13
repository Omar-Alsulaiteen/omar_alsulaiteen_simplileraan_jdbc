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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AssignSubject
 */
@WebServlet("/assignSubject")
public class AssignSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResultSet subjects;
		ResultSet classes;
		Database db = (Database) getServletContext().getAttribute("db");
		RequestDispatcher rd = request.getRequestDispatcher("assignSubject.jsp");
		String qry = "select * from subject"
				+ "    order by name asc;";
		
		String qry2 = "select * from class;";
		
		try {
			subjects = db.runSql(qry);
			classes = db.runSql(qry2);
			
			request.setAttribute("subjects", subjects);
			request.setAttribute("classes", classes);

			rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error retriving the subjects or classes' list");
		}
	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("subject");
		String c = request.getParameter("class");
		
		if (s == null || c == null) {
			doGet(request, response);
			return;
		}
		int subjectID = Integer.parseInt(s);
		int classID = Integer.parseInt(c);
		
		String qry = String.format("insert into class_subject(class_id, subject_id) values(%d, %d)", classID, subjectID);
		Database db = (Database) getServletContext().getAttribute("db");

		Statement statement;
		HttpSession session = request.getSession();

		try {
			statement = db.getConnection().createStatement();
			int result = statement.executeUpdate(qry);
			if (result <= 1)
				session.setAttribute("message", "class - subject Assigned Sucessfully");
			else
				session.setAttribute("message", "class - subject wasn't Assigned to the database");
			
		} catch(SQLIntegrityConstraintViolationException e) {
			session.setAttribute("message", "This subject is already added to this class");
			e.printStackTrace();

			
		}	catch (SQLException e) {
			session.setAttribute("message", "Error assigning class - subject to the database");
			e.printStackTrace();
		}
		
		doGet(request, response);

		
	}

}
