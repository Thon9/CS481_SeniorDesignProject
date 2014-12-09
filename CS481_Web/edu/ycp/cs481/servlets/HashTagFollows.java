

package edu.ycp.cs481.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs481.srdesign.HashTag;
import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.PhotoUI;
import edu.ycp.cs481.srdesign.controllers.GetHashtagsUserFollowingController;
import edu.ycp.cs481.srdesign.controllers.GetPhotosByUserID;
import edu.ycp.cs481.srdesign.controllers.GetPhotosFollowingHashtag;

/**
 * Servlet implementation class
*/
@WebServlet("/HashTagFollows")
@MultipartConfig
public class HashTagFollows extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//show a list of follow hashtag
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    	ArrayList<HashTag> hashtag = new ArrayList<HashTag>();
    	GetHashtagsUserFollowingController tagController = new GetHashtagsUserFollowingController();
		
		//get user following hashtag name
		HttpSession session = request.getSession();
    	int userID = (int) session.getAttribute("userID");
    		
    	//if no hashtag
    	try {
			if (tagController.GetPhotosFollowingHashtagID(userID) == null){
				System.out.println("HashTagF: user has no followed Tags");
			}
			
			//there are hashtags
			else {
				//get the hashtag name
				hashtag.addAll(tagController.GetPhotosFollowingHashtagID(userID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	request.setAttribute("hashtagList", hashtag);
    	session.setAttribute("hashtagList", hashtag);
		request.getRequestDispatcher("/followhashtag.jsp").forward(request, response); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}
	
}
