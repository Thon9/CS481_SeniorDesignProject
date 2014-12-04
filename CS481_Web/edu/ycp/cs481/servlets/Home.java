

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

import edu.ycp.cs481.srdesign.Photo;
import edu.ycp.cs481.srdesign.controllers.GetPhotosByUserID;
import edu.ycp.cs481.srdesign.controllers.GetPhotosFollowingHashtag;

/**
 * Servlet implementation class
*/
@WebServlet("/Home")
@MultipartConfig
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    @Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

    	ArrayList<Photo>temp = new ArrayList<Photo>();
    	ArrayList<String> paths = new ArrayList<String>();
		GetPhotosFollowingHashtag photoController = new GetPhotosFollowingHashtag();
		
		//get user following hashtag photos
		HttpSession session = request.getSession();
    	int userID = (int) session.getAttribute("userID");
    	try {
			temp = photoController.GetPhotosFollowingHashtag(userID, 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    			
    	if (temp == null){
			System.out.println("Home: user has no followed photos");
		}
		else {
			
			System.out.println(temp.size());
			for(int i = 0; i < temp.size(); i++){
				System.out.println("Home: Counter is at " + i);
				System.out.println("Home: PhotoID is " + temp.get(i).getphotoID());
				System.out.println("Home: User ID is " + temp.get(i).getuserID());
			}
			
			for(int i = 0; i < temp.size(); i++){
				System.out.println("Home: The PHOTO ID of temp photo " + i + " is " + temp.get(i).getphotoID());
				// NEEDS TO BE FIXED, HARDCORDED
				paths.add("image/"+(temp.get(i).getphotoID()-temp.size()+i+1));			
			}
		}
		request.setAttribute("photoList", paths);
		request.getRequestDispatcher("/main.jsp").forward(request, response); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}
	
}
