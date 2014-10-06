package edu.ycp.cs481.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.controllers.CheckExistUserController;
import edu.ycp.cs481.srdesign.controllers.LoginController;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get username and password
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println("test 1");
		
		//if username and password is NOT null or empty
		if (userName != null && password != null && !userName.isEmpty() && !password.isEmpty()){
			
			//check if user exist
			System.out.println(userName + " "+ password);
			LoginController controller = new LoginController();
			System.out.println("test 2");
			User user = controller.login(userName, password);
			System.out.println("test 3");
			
			if (user != null){
				//user exist
				HttpSession session = request.getSession();
				session.setAttribute("User", user);
			}
			
			//to main
			response.sendRedirect(request.getContextPath()+"/main.jsp");
			request.setAttribute("result", "");
			this.doGet(request, response);
		}
		else {
			request.setAttribute("result", "user and/or password fields are empty");
			this.doGet(request, response);
		}
		
	}
}
