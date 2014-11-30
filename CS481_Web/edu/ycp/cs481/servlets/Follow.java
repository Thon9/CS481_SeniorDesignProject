

package edu.ycp.cs481.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class
*/
@WebServlet("/Follow")
@MultipartConfig
public class Follow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	response.setStatus(HttpServletResponse.SC_OK);
    	request.getRequestDispatcher("/gallery.jsp").forward(request, response); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String hashtag = request.getParameter("search_object");
		System.out.println("Following "+hashtag);
		//do nothing with no hashtag to follow
		if (hashtag == null){
			request.setAttribute("result", "no hashtag to follow");
			this.doGet(request, response);
		}
		
		//follow hashtag
		else {
			response.sendRedirect(request.getContextPath()+"/main.jsp");
		}
	}
}

