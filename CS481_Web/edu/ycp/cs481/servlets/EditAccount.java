

package edu.ycp.cs481.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class
*/
@WebServlet("/EditUser")
@MultipartConfig
public class EditAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	response.setStatus(HttpServletResponse.SC_OK);
    	request.getRequestDispatcher("/main.jsp").forward(request, response); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Changing Account information...");
    	String firstName = request.getParameter("firstname");
    	String lastName = request.getParameter("lastname");
    	String userName = request.getParameter("userName");
    	String password = request.getParameter("password");
    	String email = request.getParameter("email");
    	System.out.println(firstName+lastName+userName+password+email);
    	if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || password.isEmpty() || email.isEmpty()){
    		request.setAttribute("result", "cannot change info");
			this.doGet(request, response);
    	}
    	else {
	    	HttpSession session = request.getSession();
	    	session.setAttribute("userName", userName);
			session.setAttribute("email", email);
			session.setAttribute("firstname", firstName);
			session.setAttribute("lastname", lastName);
			session.setAttribute("password", password);
			
	    	response.sendRedirect(request.getContextPath()+"/main.jsp");
    	}
	}
}

