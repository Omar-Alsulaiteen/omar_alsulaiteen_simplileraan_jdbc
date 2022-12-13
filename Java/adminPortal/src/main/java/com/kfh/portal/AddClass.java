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
 * Servlet implementation class AddClass
 */
@WebServlet("/addClass")
public class AddClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String qry = "select * from class";
		Database db = (Database) getServletContext().getAttribute("db");
		RequestDispatcher rd = request.getRequestDispatcher("addClass.jsp");
		
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
		String syear = request.getParameter("year");
		String snumber = request.getParameter("number");
//		RequestDispatcher rd = request.getRequestDispatcher("addClass.jsp");

		if (syear == null || snumber == null) {
			request.setAttribute("message", "Please enter a valid value");
			doGet(request, response);
			return;
		}
		
		int year = Integer.parseInt(syear);
		int number = Integer.parseInt(snumber);
		

		String qry = String.format("insert into class(year, number) values (%d, %d)", year, number);

		Statement statement;
		Database db = (Database) getServletContext().getAttribute("db");

		try {
			statement = db.getConnection().createStatement();
			int result = statement.executeUpdate(qry);
			if (result <= 1)
				request.setAttribute("message", "The class was Assigned Sucessfully");
			else
				request.setAttribute("message", "The class wasn't Assigned to the database");

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("This class already exists in the database");
			request.setAttribute("message", "This class already exist in the database");
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("message", "Error assigning class to the database");
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
