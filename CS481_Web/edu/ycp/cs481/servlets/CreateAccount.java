package edu.ycp.cs481.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.controllers.AddNewUserController;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/createAccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get added userinfo from textboxes
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String checkPassword = request.getParameter("passwordConfirm");
		String checkEmail = request.getParameter("emailConfirm");
		System.out.println("test 1");
		//check if empty textboxes
		if (userName.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()){
			System.out.println("test 2a");
			request.setAttribute("result", "infomation is incomplete");
			this.doGet(request, response);
		}
		//check passwords are the same
		else if (!password.equals(checkPassword)){
			System.out.println("test 2b");
			request.setAttribute("result", "both passwords dont match");
			this.doGet(request, response);
		}
		//check email are same
		else if (!email.equals(checkEmail)){
			System.out.println("test 2c");
			request.setAttribute("result", "both emails are incorrect");
			this.doGet(request, response);
		}
		//then go to main page with user info
		else {
			System.out.println("test 2d");
			//add user
			User newUser = new User();
			newUser.setUserName(userName);
			newUser.setPassword(password);
			newUser.setFirstName(firstName);
			newUser.setLastName(lastName);
			newUser.setUserEmail(email);			
			System.out.println("test 2d.1");
			AddNewUserController controller = new AddNewUserController();
			System.out.println("test 2d.2");
			//controller should add user to DB
			controller.addNewUser(newUser);
			System.out.println("test 2d.3");
			
			response.sendRedirect(request.getContextPath()+"/main.jsp");
			request.setAttribute("result", "");
			this.doGet(request, response);
		}
	}

}