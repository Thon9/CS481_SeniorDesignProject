package edu.ycp.cs481.servlets;

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
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		System.out.println("test 1get");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get username and password
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");



		//if username and password is NOT null or empty
		if (userName != null && password != null && !userName.isEmpty() && !password.isEmpty()){

			//check if user exist
			System.out.println(userName + " "+ password);
			//controller should check for the user in the database

			LoginController controller = new LoginController();

			try {
				user = controller.login(userName, password);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			//if user is null 
			try {
				if (controller.login(userName, password) == null){	
					System.out.println("Incorrect username/password");
					request.setAttribute("result", "incorrect user/password");
					this.doGet(request, response);
				}
				else {
					System.out.println("Success");

					System.out.println(controller.login(userName, password).getUserName());

					//user exist
					HttpSession session = request.getSession();
					session.setAttribute("userName", controller.login(userName, password).getUserName());
					session.setAttribute("userID", controller.login(userName, password).getuserID());
					session.setAttribute("email", controller.login(userName, password).getUserEmail());
					session.setAttribute("firstname", controller.login(userName, password).getFirstName());
					session.setAttribute("lastname", controller.login(userName, password).getLastName());
					//to main
					//request.setAttribute("UserName", "");
					response.sendRedirect(request.getContextPath()+"/main.jsp");

					//this.doGet(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			request.setAttribute("result", "user and/or password fields are empty");
			this.doGet(request, response);
		}

	}
}

