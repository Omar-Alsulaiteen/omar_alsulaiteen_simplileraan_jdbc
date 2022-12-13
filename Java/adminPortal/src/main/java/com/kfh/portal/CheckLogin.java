package com.kfh.portal;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLogin {
	public static String checkLogin(HttpSession session, HttpServletResponse response) throws IOException {
		String user;
		Object u = session.getAttribute("user");
		if (u == null) {
			response.sendRedirect(response.encodeRedirectURL("index.jsp"));
			return null;
		} else {
			user = u.toString();
			return user;
		}
	}
}
