package com.kfh.portal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Database {
 
	private Connection conn = null;
 
	public Database(String url, String user_name, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
 
			this.conn = DriverManager.getConnection(url, user_name, password);
			System.out.println("Success");
 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error defining class");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error defining database");

		}
	}
 
	public Connection getConnection() {
		return this.conn;
	}
 
	public ResultSet runSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}
}
