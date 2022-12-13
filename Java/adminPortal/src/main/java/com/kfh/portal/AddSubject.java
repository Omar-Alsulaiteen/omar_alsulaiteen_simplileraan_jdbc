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
 * Servlet implementation class AddSubject
 */
@WebServlet("/addSubject")
public class AddSubject extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String qry = "select * from subject";
		Database db = (Database) getServletContext().getAttribute("db");
		RequestDispatcher rd = request.getRequestDispatcher("addSubject.jsp");
		
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
		String name = request.getParameter("name");

		if (name == null) {
			request.setAttribute("message", "Please enter a valid value");
			doGet(request, response);
			return;
		}

		String qry = String.format("insert into subject(name) values ('%s')", name);

		Statement statement;
		Database db = (Database) getServletContext().getAttribute("db");

//		RequestDispatcher rd = request.getRequestDispatcher("addSubject.jsp");

		try {
			statement = db.getConnection().createStatement();
			int result = statement.executeUpdate(qry);
			if (result <= 1)
				request.setAttribute("message", "Subject Assigned Sucessfully");
			else
				request.setAttribute("message", "Subject wasn't Assigned to the database");

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("a Subject with same name already exists");
			request.setAttribute("message", "a Subject with same name already exists");
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("message", "Error assigning Subject to the database");
			e.printStackTrace();
		}

		doGet(request, response);

	}

}
