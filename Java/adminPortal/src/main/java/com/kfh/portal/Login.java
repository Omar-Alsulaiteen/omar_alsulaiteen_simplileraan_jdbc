package com.kfh.portal;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
//		System.out.println(email + " " + password);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");

		if(email == null || password == null) {
			session.setAttribute("message", "invalid username or password");
//			response.sendRedirect("index.jsp");
			rd.forward(request, response);
			return;
		}
		Database db = (Database) getServletContext().getAttribute("db");

		try {
			ResultSet result = db.runSql("select * from admin where email='" + email + "' and password='" + password + "'");
			if(result.next()) {
				System.out.println("Here Result");
				String email2 = result.getString("email");
				if (email2 == null) {
					session.setAttribute("message", "Something wrong happned while signing you in. Please contact the admin");
					response.sendRedirect(response.encodeRedirectURL("index.jsp"));
					return;
				}
				session.setAttribute("user", email2);
				session.setAttribute("first name", result.getString("first_name"));
				session.setAttribute("last name", result.getString("last_name"));
				response.sendRedirect(response.encodeRedirectURL("homePage"));
			} else {
				request.setAttribute("message", "Invalid username or password");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			request.setAttribute("message", "Something wrong happned with the database. Please contact the admin");
//			response.sendRedirect("error.jsp");
			e.printStackTrace();
		}
	}
}
