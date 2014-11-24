package edu.ycp.cs481.servlets;
/*
 * 
 * DOES NOT WORK, NEED TO RESET INFORMATION
 * 
 * 
 */
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// import org.apache.catalina.connector.Request;


import edu.ycp.cs481.srdesign.User;

// import edu.ycp.cs481.srdesign.controllers.CheckExistUserController;

import edu.ycp.cs481.srdesign.controllers.LoginController;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/logout.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get username and password
		HttpSession session = request.getSession();
		//remove the user from session
		session.removeAttribute("userName");
		session.removeAttribute("userID");
		session.removeAttribute("email");
		session.removeAttribute("firstname");
		session.removeAttribute("lastname");
		
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
}

