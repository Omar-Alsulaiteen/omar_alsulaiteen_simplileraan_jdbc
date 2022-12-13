package com.kfh.portal;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
 
/**
 * Application Lifecycle Listener implementation class myServletListener
 *
 */
@WebListener
public class DatabaseListener implements ServletContextListener {
 
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
 
    	String url = "jdbc:mysql://localhost:3306/portal";
    	String dbUsername = "root";
    	String dbPassword = "";

    	ServletContext sc = event.getServletContext();
 
    	Database db = new Database(url, dbUsername, dbPassword);
    	System.out.println("in the listener!!");
    	
    	sc.setAttribute("db", db);
 
    }
 
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
 
}
