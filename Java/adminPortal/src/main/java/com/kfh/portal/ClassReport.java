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

/**
 * Servlet implementation class ClassReport
 */
@WebServlet("/classReport")
public class ClassReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		System.out.println(cid);
		String classQuery = "select * from class";
		Database db = (Database) getServletContext().getAttribute("db");
		String qry = "select c.year, c.number, i.first_name, i.last_name, s.name"
				+ " from class_subject cs"
				+ "  inner join class c on cs.class_id=c.id"
				+ "  inner join subject s on cs.subject_id=s.id"
				+ "  inner join instructor i on cs.instructor_id=i.id"
				+ "  where c.id=" + cid;
		String studentQuery = "select * from student where class_id=" + cid;

		RequestDispatcher rd = request.getRequestDispatcher("classReport.jsp");
		try {
			ResultSet classes = db.runSql(classQuery);
			request.setAttribute("classes", classes);
			
			if (cid == null) {
				rd.forward(request, response);
				return;
			}

			ResultSet students = db.runSql(studentQuery);
			request.setAttribute("students", students);
						
			ResultSet report = db.runSql(qry);
			request.setAttribute("report", report);


		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
