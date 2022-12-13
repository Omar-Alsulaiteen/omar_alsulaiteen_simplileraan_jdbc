//package com.kfh.portal;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.sql.SQLIntegrityConstraintViolationException;
//import java.sql.Statement;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * Servlet implementation class SignUp
// */
//@WebServlet("/signUp")
//public class SignUp extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String email = request.getParameter("email");
//		String fname = request.getParameter("first_name");
//		String lname = request.getParameter("last_name");
//		String password = request.getParameter("password");
//		
//		String qry = String.format("insert into admin(email, first_name, last_name, password) values('%s', '%s', '%s', '%s');", email, fname, lname, password);
//
//		RequestDispatcher rd2 = request.getRequestDispatcher("signUp.jsp");
//		HttpSession session = request.getSession();
//
//		boolean isNull = email == null || fname == null || lname == null || password == null;
//		
//		if (isNull) {
//			session.setAttribute("message", "Please enter all the values");
//			rd2.forward(request, response);
//			return;
//		}
//		boolean isEmpty = email.isEmpty() || fname.isEmpty() || lname.isEmpty() || password.isEmpty();
//		if(isEmpty) {
//			session.setAttribute("message", "Please enter all the values");
//			rd2.forward(request, response);
//			return;
//
//		}
//
//		
//		Database db = (Database) getServletContext().getAttribute("db");
//		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
//		
//		try {
//			Statement statement = db.getConnection().createStatement();
//			statement.executeUpdate(qry);
//			session.setAttribute("message", "Signed up successfully");
//			rd.forward(request, response);
//			
//		} catch(SQLIntegrityConstraintViolationException e) {
//			System.out.println("Account with the same email already exists");
//			session.setAttribute("message", "Account with the same email already exists");
//			e.printStackTrace();
//			rd2.forward(request, response);
//		
//		} catch (SQLException e) {
//			System.out.println("Error adding a new admin to the database");
//			session.setAttribute("message", "Error from the database. Please contact the admin");
//			e.printStackTrace();
//			rd2.forward(request, response);
//		}
//	}
//
//}
