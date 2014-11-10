package edu.ycp.cs481.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs481.srdesign.User;
import edu.ycp.cs481.srdesign.controllers.AddNewUserController;
import edu.ycp.cs481.srdesign.controllers.DeleteUserController;

/**
 * Servlet implementation class AccountInformation
 */
@WebServlet("/AccountInformation")
public class DeleteAccount extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	response.setStatus(HttpServletResponse.SC_OK);
		request.getRequestDispatcher("/createAccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Delete user
		
		DeleteUserController delete = new DeleteUserController();
		HttpSession session = request.getSession();
		
		int userID = (int) session.getAttribute("userID");
		
			try {
				delete.deleteUser(userID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
		
			request.setAttribute("result", "success");
			//this.doGet(request, response);
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			} 
	}
}
