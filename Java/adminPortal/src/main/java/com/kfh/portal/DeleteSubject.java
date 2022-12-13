package com.kfh.portal;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteSubject
 */
@WebServlet("/deleteSubject")
public class DeleteSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher rd = request.getRequestDispatcher("addClass");
//		rd.forward(request, response);
		response.sendRedirect("addSubject");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String qry;
		Database db = (Database) getServletContext().getAttribute("db"); 
		try {
			Statement statement = db.getConnection().createStatement();
			if (id != null) {
				qry = "delete from subject where id=" + id;
				int r = statement.executeUpdate(qry);
				if (r >= 1) {
					request.setAttribute("message", "deleted successfully");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().print("<h2 style=\"color:green;\"> Deleted Sucessfully</h2><br><br>");
		doGet(request, response);
	}
}
