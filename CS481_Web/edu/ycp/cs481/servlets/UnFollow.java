

package edu.ycp.cs481.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs481.srdesign.controllers.AddFollowHashTag;
import edu.ycp.cs481.srdesign.controllers.CheckUserFollowingHashtagController;
import edu.ycp.cs481.srdesign.controllers.GetHashtagIDFromString;
import edu.ycp.cs481.srdesign.controllers.GetPhotosFollowingHashtag;
import edu.ycp.cs481.srdesign.controllers.UserUnfollowHashtagController;

/**
 * Servlet implementation class
*/
@WebServlet("/UnFollow")
@MultipartConfig
public class UnFollow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	response.setStatus(HttpServletResponse.SC_OK);
    	request.getRequestDispatcher("/gallery.jsp").forward(request, response); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userID = (int) session.getAttribute("userID");
		GetHashtagIDFromString hashIDcont = new GetHashtagIDFromString();
		
		//do nothing with no hashtag to follow
		if (session.getAttribute("hashTag") == null){
			request.setAttribute("result", "no hashtag to follow");
			this.doGet(request, response);
		}
		
		//unfollow hashtag
		else {
			// Controllers to check if user is following hashtag and unfollow
			CheckUserFollowingHashtagController checkfollowing = new CheckUserFollowingHashtagController();
			UserUnfollowHashtagController unfollow = new UserUnfollowHashtagController();
			try {
				System.out.println("UnFollow: " + session.getAttribute("hashTag").toString());
				int hashID = hashIDcont.getHashtagID(session.getAttribute("hashTag").toString());
				boolean following = checkfollowing.checkUserFollowingHashtag(hashID, userID);
				if(following){
					unfollow.userUnfollowHashtag(hashID, userID);
				} else {
					System.out.println("The user is not following this hashtag");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
			response.sendRedirect(request.getContextPath()+"/Home");
		}
	
	}

