

package edu.ycp.cs481.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs481.srdesign.controllers.AddFollowHashTag;
import edu.ycp.cs481.srdesign.controllers.GetPhotosFollowingHashtag;

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
		HttpSession session = request.getSession();
		
		//do nothing with no hashtag to follow
		if (session.getAttribute("hashTag") == null){
			request.setAttribute("result", "no hashtag to follow");
			this.doGet(request, response);
		}
		
		//follow hashtag
		else {
			AddFollowHashTag followController = new AddFollowHashTag();
			followController.addFollowingHashTag(session.getAttribute("hashTag")) == true){
				
			}
			String hashtag = session.getAttribute("hashTag").toString();
			System.out.println("Fol: Following "+hashtag);
			response.sendRedirect(request.getContextPath()+"/main.jsp");
		}
	}
}

